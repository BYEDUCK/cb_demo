package com.example.cbDemo.service

import com.example.cbDemo.domain.Beer

interface BeerService {

    fun findByName(name: String): Beer

    fun findAll(pageNum: Int, pageSize: Int): List<Beer>

    fun findAll(): List<Beer>

}