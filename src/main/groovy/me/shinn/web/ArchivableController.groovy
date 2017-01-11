package me.shinn.web

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*

import javax.inject.Inject

@RestController
@RequestMapping("/archivable")
class ArchivableController {

    @Inject
    ArchivableManager manager

    @GetMapping("")
    @ResponseBody
    Collection<ArchivingJob> list() {
        return manager.queryStatus()
    }

    @PostMapping("")
    @ResponseBody
    String post(@RequestBody ArchivableRequest request) {
        return manager.run(request.url)
    }

    @MessageMapping("/archivable")
    @SendTo("/topic/status")
    String statusQuery(String id) {

    }
}
