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
    fun findAllPaged(
            @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
            @RequestParam(value = "size", required = false, defaultValue = "10") size: Int
    ): List<Beer> {
        return beerService.findAll(page, size)
    }

    @GetMapping("/beers/all")
    fun findAll(): List<Beer> {
        return beerService.findAll()
    }

    @GetMapping("/beers/byName")
    fun findByName(@RequestParam(value = "name", required = true) name: String): Beer {
        return beerService.findByName(name)
    }

}