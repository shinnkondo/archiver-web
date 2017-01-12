package me.shinn.archiver.web.common

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class StatusController {
    @RequestMapping("/status")
    @ResponseBody
    String status() {
        return 'I am running'
    }
}
