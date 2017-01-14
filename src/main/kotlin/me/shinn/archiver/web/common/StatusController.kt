package me.shinn.archiver.web.common

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api")
class StatusController(val config: UserConfig) {
    @RequestMapping("/status")
    @ResponseBody
    fun status() = "I am running."
}
