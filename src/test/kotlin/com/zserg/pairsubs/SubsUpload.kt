package com.zserg.pairsubs

import com.zserg.pairsubs.model.*
import com.zserg.pairsubs.srtparser.SRTParser
import com.zserg.pairsubs.utils.SrtUtils
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.web.client.RestTemplate


class SubsUpload {

    @Test
    fun upload() {
        val subs_ru = read("subtitles/1x02 Purple Giraffe.srt", Language.RU)
        val subs_en = read("subtitles/How.I.Met.Your.Mother.S01E02.Purple Giraffe.srt", Language.EN)

        val pairSubs = PairSubs(title = "How I met your mother (S01E02)",
            subs1 = Subs(Language.RU, subs_ru),
            subs2 = Subs(Language.EN, subs_en),
            config = PairSubsConfig(3, 456, 1, 402)
        )

        val restTemplate = RestTemplate()
        val request: HttpEntity<PairSubs> = HttpEntity<PairSubs>(pairSubs)
        val response =
            restTemplate.postForObject("http://localhost:8080/pairsubs", request, String::class.java)


        assertTrue(true)
    }

    private fun read(file: String, language: Language): List<SubItem> {
        val subs = SRTParser.getSubtitlesFromFile(file)
        val items = subs?.map { SrtUtils.subtitleToSubItem(it) } ?: emptyList()
        return items
    }

}