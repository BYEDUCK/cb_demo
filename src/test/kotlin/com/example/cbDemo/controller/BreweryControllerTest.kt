package com.example.cbDemo.controller

import com.example.cbDemo.domain.Brewery
import com.example.cbDemo.service.impl.BreweryServiceImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.json.JSONArray
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(MockitoJUnitRunner::class)
class BreweryControllerTest {

    @Mock
    private lateinit var breweryServiceImpl: BreweryServiceImpl

    @InjectMocks
    private lateinit var breweryController: BreweryController

    private lateinit var mockMvc: MockMvc

    private val stringArgumentCaptor: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
    private val testBrewery: Brewery = Brewery()
    private val testBreweryList: List<Brewery> = listOf(testBrewery)
    private val gson = Gson()


    @Before
    fun setUp() {
        testBrewery.name = "testBrewery"
        mockMvc = MockMvcBuilders.standaloneSetup(breweryController).build()
        whenever(breweryServiceImpl.findAll()).thenReturn(testBreweryList)
        whenever(breweryServiceImpl.countByCountry(Mockito.anyString())).thenReturn(1)
    }

    @Test
    fun findAllTest() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get("/breweries"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn()

        val resultJsonArray = JSONArray(result.response.contentAsString)
        val resultArray = gson.fromJson<List<Brewery>>(resultJsonArray.toString(), object : TypeToken<List<Brewery>>() {}.type)
        assertThat(resultArray).isNotNull.isNotEmpty.contains(testBrewery)
    }

    @Test
    fun countByCountryCorrectTest() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get("/breweries/count?country=test"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn()

        verify(breweryServiceImpl, times(1)).countByCountry(stringArgumentCaptor.capture())
        assertThat(stringArgumentCaptor.value).isEqualTo("test")
        val count = result.response.contentAsString.toInt()
        assertThat(count).isEqualTo(1)
    }
}