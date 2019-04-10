package com.example.cbDemo.controller

import com.example.cbDemo.domain.Brewery
import com.example.cbDemo.service.BreweryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BreweryController(@Autowired private var breweryService: BreweryService) {

    @GetMapping("/breweries")
    fun findAll(): List<Brewery> {
        return breweryService.findAll()
    }

    @GetMapping("/breweries/count")
    fun countByCountry(@RequestParam(value = "country", required = false) country: String?): Int {
        return breweryService.countByCountry(country)
    }

}