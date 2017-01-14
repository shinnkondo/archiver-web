package me.shinn.archiver.web.archivable

import me.shinn.archiver.web.model.ArchivableRequest
import me.shinn.archiver.web.model.ArchivingJob
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*

import javax.inject.Inject

@RestController
@RequestMapping("/archivable")
class ArchivableController(val manager: ArchivableManager) {


    @GetMapping("")
    @ResponseBody
    fun list() = manager.queryStatus()

    @PostMapping("")
    @ResponseBody
    fun post(@RequestBody request: ArchivableRequest ) = manager.run(request.url)

    @MessageMapping("/archivable")
    @SendTo("/topic/status")
    fun statusQuery(id: String): String {
        throw NotImplementedError()
    }
}
