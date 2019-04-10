package com.example.cbDemo.service

import com.example.cbDemo.domain.Brewery

interface BreweryService {

    fun findAll(): List<Brewery>

    fun countByCountry(country: String?): Int

}