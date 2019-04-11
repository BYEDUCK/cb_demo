package com.example.cbDemo.repository

import com.example.cbDemo.domain.Beer
import org.springframework.data.couchbase.core.query.View
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BeerRepository : CrudRepository<Beer, String> {

    @View(designDocument = "beer", viewName = "by_name", reduce = false)
    fun findBeerByName(name: String): List<Beer>

}
