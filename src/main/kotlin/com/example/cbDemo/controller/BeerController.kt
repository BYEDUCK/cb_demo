package com.example.cbDemo.controller

import com.example.cbDemo.domain.Beer
import com.example.cbDemo.service.BeerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BeerController(@Autowired private var beerService: BeerService) {

    @GetMapping("/beers")
    fun findAll(): List<Beer> {
        return beerService.findAll()
    }

    @GetMapping("/beers/byName")
    fun findByName(@RequestParam(value = "name", required = true) name: String): Beer {
        val found = beerService.findByName(name)
        return if (found.size == 1) found[0] else Beer()
    }

}