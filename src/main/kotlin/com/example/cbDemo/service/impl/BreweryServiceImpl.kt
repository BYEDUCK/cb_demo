package com.example.cbDemo.service.impl

import com.example.cbDemo.domain.Brewery
import com.example.cbDemo.repository.BreweryRepository
import com.example.cbDemo.service.BreweryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BreweryServiceImpl(@Autowired private var breweryRepository: BreweryRepository) : BreweryService {

    override fun countByCountry(country: String?): Int {
        return breweryRepository.countBreweriesByCountry(country)
    }

    override fun findAll(): List<Brewery> {
        return breweryRepository.findAll().toList()
    }

}