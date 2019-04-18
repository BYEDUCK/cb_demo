package com.example.cbDemo.controller

import com.example.cbDemo.domain.Beer
import com.example.cbDemo.service.impl.BeerServiceImpl
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
class BeerControllerTest {

    @Mock
    private lateinit var beerServiceImpl: BeerServiceImpl

    @InjectMocks
    private lateinit var beerController: BeerController

    private lateinit var mockMvc: MockMvc
    private val gson = Gson()

    private val testBeer: Beer = Beer()
    private val testBeerList: List<Beer> = listOf(testBeer)
    private val intArgumentCaptor: ArgumentCaptor<Int> = ArgumentCaptor.forClass(Int::class.java)

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(beerController).build()
        testBeer.name = "testBeer"
        whenever(beerServiceImpl.findAll()).thenReturn(testBeerList)
        whenever(beerServiceImpl.findAll(Mockito.anyInt(), Mockito.anyInt())).thenReturn(testBeerList)
        whenever(beerServiceImpl.findByName(Mockito.anyString())).thenReturn(testBeer)
    }

    @Test
    fun findAllPagedWithRequiredParametersTest() {
        val response = mockMvc.perform(MockMvcRequestBuilders.get("/beers?page=0&size=10"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn()

        verify(beerServiceImpl, times(1)).findAll(intArgumentCaptor.capture(), intArgumentCaptor.capture())
        val args = intArgumentCaptor.allValues
        assertThat(args[0]).isEqualTo(0)
        assertThat(args[1]).isEqualTo(10)

        val resultArrayJson = JSONArray(response.response.contentAsString)
        val resultArray = gson.fromJson<List<Beer>>(resultArrayJson.toString(), object : TypeToken<List<Beer>>() {}.type)
        assertThat(resultArray).isNotNull.isNotEmpty.hasSize(1).contains(testBeer)
    }

    @Test
    fun findAllPagedWithoutRequiredParametersTest() {
        val response = mockMvc.perform(MockMvcRequestBuilders.get("/beers"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn()

        verify(beerServiceImpl, times(1)).findAll(intArgumentCaptor.capture(), intArgumentCaptor.capture())
        val args = intArgumentCaptor.allValues
        assertThat(args[0]).isEqualTo(0)
        assertThat(args[1]).isEqualTo(10)

        val resultArrayJson = JSONArray(response.response.contentAsString)
        val resultArray = gson.fromJson<List<Beer>>(resultArrayJson.toString(), object : TypeToken<List<Beer>>() {}.type)
        assertThat(resultArray).isNotNull.isNotEmpty.hasSize(1).contains(testBeer)
    }

    @Test
    fun findAllTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/beers/all"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
    }

    @Test
    fun findByNameCorrectTest() {
        val response = mockMvc.perform(MockMvcRequestBuilders.get("/beers/byName?name=test"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn()

        verify(beerServiceImpl, times(1)).findByName("test")

        val foundBeer = gson.fromJson<Beer>(response.response.contentAsString, object : TypeToken<Beer>() {}.type)
        assertThat(foundBeer).isEqualTo(testBeer)
    }

    @Test
    fun findByNameWithoutNameTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/beers/byName"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }
}