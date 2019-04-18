package com.example.cbDemo.service.impl

import com.example.cbDemo.domain.Beer
import com.example.cbDemo.repository.BeerRepository
import com.example.cbDemo.service.BeerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class BeerServiceImpl(@Autowired private var beerRepository: BeerRepository) : BeerService {

    override fun findAll(): List<Beer> {
        return beerRepository.findAll().toList()
    }

    override fun findAll(pageNum: Int, pageSize: Int): List<Beer> {
        val pageFound = beerRepository.findAll(PageRequest.of(pageNum, pageSize))
        return pageFound.content
    }

    override fun findByName(name: String): Beer {
        val found = beerRepository.findBeerByName(name)
        return found[0]
    }

}