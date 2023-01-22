package com.zserg.pairsubs.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalTime

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
class PairSubs(
    @Id
    var id: String? = null,
    var title: String,
    var subs1: Subs,
    var subs2: Subs,
    var config: PairSubsConfig = PairSubsConfig()
    )
