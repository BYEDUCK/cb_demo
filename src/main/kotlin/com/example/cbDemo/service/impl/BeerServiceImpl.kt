package com.example.cbDemo.service.impl

import com.example.cbDemo.domain.Beer
import com.example.cbDemo.repository.BeerRepository
import com.example.cbDemo.service.BeerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BeerServiceImpl(@Autowired private var beerRepository: BeerRepository) : BeerService {

    override fun findByName(name: String): List<Beer> {
        return beerRepository.findBeerByName(name)
    }

    override fun findAll(): List<Beer> {
        return beerRepository.findAll().toList()
    }

}