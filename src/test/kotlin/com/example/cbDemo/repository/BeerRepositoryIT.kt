package com.example.cbDemo.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
internal class BeerRepositoryIT {

    @Autowired
    private lateinit var beerRepository: BeerRepository

    @Test
    fun findAll() {
        val found = beerRepository.findAll().toList()
        assertThat(found).isNotNull.isNotEmpty
    }

    @Test
    fun findAllWithDefaultPage() {
        val found = beerRepository.findAll(PageRequest.of(0, 10, Sort(Sort.DEFAULT_DIRECTION, "name")))
        assertThat(found.content).isNotNull
    }

}