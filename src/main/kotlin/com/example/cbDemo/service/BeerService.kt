package com.example.cbDemo.service

import com.example.cbDemo.domain.Beer

interface BeerService {

    fun findAll(): List<Beer>

}