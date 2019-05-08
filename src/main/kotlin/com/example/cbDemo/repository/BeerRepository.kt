package com.example.cbDemo.repository

import com.example.cbDemo.domain.Beer
import org.springframework.data.couchbase.core.query.View
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface BeerRepository : CouchbasePagingAndSortingRepository<Beer, String> {

    @View(designDocument = "beer", viewName = "by_name", reduce = false)
    fun findBeerByName(name: String): List<Beer>

}
