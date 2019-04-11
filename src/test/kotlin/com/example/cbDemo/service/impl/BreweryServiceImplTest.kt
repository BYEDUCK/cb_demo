package com.example.cbDemo.service.impl

import com.example.cbDemo.domain.Brewery
import com.example.cbDemo.repository.BreweryRepository
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class BreweryServiceImplTest {

    @Mock
    private lateinit var breweryRepository: BreweryRepository

    @InjectMocks
    private lateinit var breweryServiceImpl: BreweryServiceImpl

    @Test
    fun countByCountry() {
        whenever(breweryRepository.countBreweriesByCountry(anyString())).thenReturn(2)
        val count = breweryServiceImpl.countByCountry("test")
        assertThat(count).isEqualTo(2)
        verify(breweryRepository, times(1)).countBreweriesByCountry(anyString())
    }

    @Test
    fun findAll() {
        val brewery = Brewery()
        whenever(breweryRepository.findAll()).thenReturn(listOf(brewery))
        val found = breweryServiceImpl.findAll()
        assertThat(found).isNotNull.contains(brewery)
        verify(breweryRepository, times(1)).findAll()
    }
}