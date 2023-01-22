package com.zserg.pairsubs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class PairsubsApplication

fun main(args: Array<String>) {
	runApplication<PairsubsApplication>(*args)
}
