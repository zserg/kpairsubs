package com.zserg.pairsubs.utils

import com.zserg.pairsubs.model.PairSubs
import com.zserg.pairsubs.model.SubItem
import com.zserg.pairsubs.srtparser.Subtitle
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SrtUtils {
    companion object {
        var timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss,SSS")

        fun subtitleToSubItem(subtitle: Subtitle): SubItem {
            return SubItem(
                id = subtitle.id,
                start = LocalTime.parse(subtitle.startTime, timeFormat),
                end = LocalTime.parse(subtitle.endTime, timeFormat),
                text = subtitle.text.toString()
            )
        }

        fun getParallelSubs(pairSubs: PairSubs, startSeconds: Long, length: Long) : Pair<List<String>, List<String>> {
            val startTime = LocalTime.ofSecondOfDay(startSeconds)
            val endTime = LocalTime.ofSecondOfDay(startSeconds + length)
            val subs1 = pairSubs.subs1.subs
                .filter { s -> s.start.isAfter(startTime) && s.end.isBefore(endTime) }
                .map(SubItem::text)
                .toList()
            val subs2 = pairSubs.subs2.subs
                .filter { s -> s.start.isAfter(startTime) && s.end.isBefore(endTime) }
                .map(SubItem::text)
                .toList()
            return Pair(subs1, subs2)

        }
    }

}