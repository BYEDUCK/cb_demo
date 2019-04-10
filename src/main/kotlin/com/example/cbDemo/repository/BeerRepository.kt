package com.example.cbDemo.repository

import com.example.cbDemo.domain.Beer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BeerRepository : CrudRepository<Beer, String>
