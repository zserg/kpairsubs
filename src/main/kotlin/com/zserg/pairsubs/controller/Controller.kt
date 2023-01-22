package com.zserg.pairsubs.controller

import com.zserg.pairsubs.model.PairSubs
import com.zserg.pairsubs.model.Subtitles
import com.zserg.pairsubs.service.PairSubsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("pairsubs")
class Controller {

    @Autowired
    private lateinit var pairSubsService: PairSubsService

    @PostMapping
    fun upload(@RequestBody pairSubs: PairSubs): String? {
        return pairSubsService.savePairSubs(pairSubs)
    }

    @GetMapping("{id}", params = ["!textOnly"])
    fun findById(@PathVariable id: String): Optional<PairSubs> {
        return pairSubsService.findById(id)
    }

    @GetMapping("{id}", params = ["textOnly=true"])
    fun findById1(
        @PathVariable id: String,
        @RequestParam textOnly: Boolean,
        @RequestParam start: Long,
        @RequestParam length: Long,
    ): Optional<Subtitles> {
        return pairSubsService.getSubtitles(id, start, length)
    }
}