package com.zserg.pairsubs.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalTime

@JsonInclude(JsonInclude.Include.NON_NULL)
class SubtitlesView(
    var title: String,
    var subs1: String,
    var subs2: String
    )
