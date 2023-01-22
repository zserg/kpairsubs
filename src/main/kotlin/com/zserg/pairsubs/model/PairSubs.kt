package com.zserg.pairsubs.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalTime

import java.time.temporal.ChronoUnit.MILLIS

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
class PairSubs(
    @Id
    var id: String? = null,
    var title: String,
    var subs1: Subs,
    var subs2: Subs,
    var config: PairSubsConfig = PairSubsConfig()
    ){

    private fun alignParams(): AlignmentParams {
        val startTime1 = subs1.subs[config.start1.toInt()-1].start
        val endTime1 = subs1.subs[config.end1.toInt()-1].end
        val startTime2 = subs2.subs[config.start2.toInt()-1].start
        val endTime2 = subs2.subs[config.end2.toInt()-1].end
//        val startTime1 = LocalTime.of(0, 1)
//        val endTime1 = LocalTime.of(0, 10)
//        val startTime2 = LocalTime.of(0, 0)
//        val endTime2 = LocalTime.of(0, 20)

        val s1 = startTime1.toSecondOfDay()
        val e1 = endTime1.toSecondOfDay()
        val s2 = startTime2.toSecondOfDay()
        val e2 = endTime2.toSecondOfDay()

        val base = startTime1
        val offsetMillis = MILLIS.between(startTime1, startTime2)
        val scale = (e2 - s2).toFloat() / (e1 - s1).toFloat()
        return AlignmentParams(base, offsetMillis, scale)
    }

    fun getIntervals(startSeconds: Long, lengthSeconds: Long): Intervals {
        val base = alignParams().base
        val offsetMillis = alignParams().offsetMillis
        val scale = alignParams().scale

        val s1 = base.plusSeconds(startSeconds)
        val e1 = s1.plusSeconds(lengthSeconds)

        var s2 = base.plusNanos(offsetMillis * 1_000_000).plusNanos((scale * startSeconds * 1_000_000_000).toLong())
        val e2 = s2.plusNanos((scale * lengthSeconds * 1_000_000_000).toLong())

        if(s2.compareTo(e2) > 0){
            s2 = LocalTime.of(0, 0, 0)
        }

        return Intervals(s1, e1, s2, e2)
    }

    data class AlignmentParams(val base: LocalTime, val offsetMillis: Long, val scale: Float)
    data class Intervals(val startTime1: LocalTime, val endTime1: LocalTime, val startTime2: LocalTime, val endTime2: LocalTime)

}
