package com.zserg.pairsubs.model

import java.time.LocalTime

class SubItem(
    val id: Int,
    val start: LocalTime,
    val end: LocalTime,
    val text: String
)