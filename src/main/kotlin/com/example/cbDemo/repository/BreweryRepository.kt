package com.example.cbDemo.repository

import com.example.cbDemo.domain.Brewery
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BreweryRepository : CrudRepository<Brewery, String> {

    fun countBreweriesByCountry(country: String?): Int

}