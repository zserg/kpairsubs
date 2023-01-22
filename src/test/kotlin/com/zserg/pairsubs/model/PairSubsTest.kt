package com.zserg.pairsubs.model

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PairSubsTest {

    @Test
    fun getIntervals() {
        val subs =
            PairSubs(title = "xxx", subs1 = Subs(Language.RU, emptyList()), subs2 = Subs(Language.EN, emptyList()))

//        val intervals = subs.getIntervals(0, 10)
//        val intervals = subs.getIntervals(0, 9*60)
        val intervals = subs.getIntervals(0, 9*60)
        assertTrue(true)
    }
}