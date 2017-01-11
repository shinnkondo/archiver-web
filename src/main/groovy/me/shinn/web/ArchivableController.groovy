package me.shinn.web

import org.springframework.web.bind.annotation.*

import javax.inject.Inject

@RestController
@RequestMapping("/archivable")
class ArchivableController {

    @Inject
    ArchivableManager manager

    @GetMapping("")
    @ResponseBody
    Collection<ArchivableRequest> list() {
        return manager.queryStatus()
    }

    @PostMapping("")
    String post(ArchivableRequest request) {
        return manager.run(request.url)
    }
}
