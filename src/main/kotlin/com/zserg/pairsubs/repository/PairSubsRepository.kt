package com.zserg.pairsubs.repository

import com.zserg.pairsubs.model.PairSubs
import org.springframework.data.mongodb.repository.MongoRepository

interface PairSubsRepository: MongoRepository<PairSubs, String> {
}