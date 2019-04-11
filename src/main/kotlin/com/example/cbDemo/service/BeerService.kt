package com.example.cbDemo.service

import com.example.cbDemo.domain.Beer

interface BeerService {

    fun findByName(name: String): Beer

    fun findAll(pageNum: Int = 1, pageSize: Int = 10): List<Beer>

}