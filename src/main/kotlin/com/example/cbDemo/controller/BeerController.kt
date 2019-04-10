package com.example.cbDemo.controller

import com.example.cbDemo.domain.Beer
import com.example.cbDemo.service.BeerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BeerController(@Autowired private var beerService: BeerService) {

    @GetMapping("/beers")
    fun findAll(): List<Beer> {
        return beerService.findAll()
    }

}