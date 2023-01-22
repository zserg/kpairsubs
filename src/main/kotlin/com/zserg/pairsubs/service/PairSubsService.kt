package com.zserg.pairsubs.service

import com.zserg.pairsubs.model.PairSubs
import com.zserg.pairsubs.model.SubItem
import com.zserg.pairsubs.model.Subtitles
import com.zserg.pairsubs.repository.PairSubsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalTime
import java.util.*

import java.time.temporal.ChronoUnit.MILLIS


@Service
class PairSubsService {

    @Autowired
    private lateinit var pairSubsRespository: PairSubsRepository

    fun savePairSubs(pairSubs: PairSubs): String? {
        val saved = pairSubsRespository.save(pairSubs)
        return saved.id
    }

    fun findById(id: String): Optional<PairSubs> {
        val paisSubs = pairSubsRespository.findById(id)
        return paisSubs
    }

    fun getSubtitles(id: String, start: Long, length: Long): Optional<Subtitles>{
        return pairSubsRespository.findById(id).map{
            p -> getParallelSubtitles(p, start, length)
        }
    }

    private fun getParallelSubtitles(pairSubs: PairSubs, start: Long, length: Long): Subtitles {
        val startTime = LocalTime.ofSecondOfDay(start)
        val endTime = LocalTime.ofSecondOfDay(start + length)

        val subtitles = Subtitles(
            pairSubs.title,
            getSubtitles(pairSubs.subs1.subs, startTime, endTime),
            getSubtitles(pairSubs.subs2.subs, startTime, endTime),
        )
        return subtitles;

    }

    private fun getSubtitles(subs: List<SubItem>, startTime: LocalTime, endTime: LocalTime): List<String> {
        return subs.filter { s -> s.start.isAfter(startTime) && s.end.isBefore(endTime) }
            .map(SubItem::text)
            .toList()
    }



}