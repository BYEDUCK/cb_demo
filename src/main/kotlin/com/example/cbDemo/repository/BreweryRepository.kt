package com.example.cbDemo.repository

import com.example.cbDemo.domain.Brewery
import org.springframework.data.couchbase.core.query.View
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface BreweryRepository : CouchbasePagingAndSortingRepository<Brewery, String> {

    @View(designDocument = "brewery", viewName = "by_country", reduce = true)
    fun countBreweriesByCountry(country: String?): Int

}