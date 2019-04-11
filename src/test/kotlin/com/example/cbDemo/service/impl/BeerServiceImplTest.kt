package com.example.cbDemo.service.impl

import com.example.cbDemo.domain.Beer
import com.example.cbDemo.repository.BeerRepository
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

@RunWith(MockitoJUnitRunner::class)
internal class BeerServiceImplTest {

    @Mock
    private lateinit var beerRepository: BeerRepository

    @InjectMocks
    private lateinit var beerServiceImpl: BeerServiceImpl

    @Test
    fun findByName() {
        val name = "test"
        val beer = Beer()
        beer.name = name
        whenever(beerRepository.findBeerByName(name)).thenReturn(listOf(beer))
        val found = beerServiceImpl.findByName(name)
        assertThat(found).isNotNull
        assertThat(found.name).isEqualTo(name)
        verify(beerRepository, times(1)).findBeerByName(anyString())
    }

    @Test
    fun findAll() {
        val beer = Beer()
        whenever(beerRepository.findAll(any(PageRequest::class.java))).thenReturn(PageImpl(listOf(beer)))
        val found = beerServiceImpl.findAll()
        assertThat(found).isNotNull.contains(beer)
        verify(beerRepository, times(1)).findAll(any(PageRequest::class.java))
    }
}