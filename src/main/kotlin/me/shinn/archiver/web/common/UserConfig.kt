package me.shinn.archiver.web.common

import me.shinn.archiver.core.config.CoreConfig
import org.springframework.beans.factory.annotation.Value
import javax.annotation.PostConstruct
import javax.inject.Named

/*
    Optional values are injected to fields.
 */
@Named
class UserConfig(@Value("\${me.shinn.workingDir}") val workingDir: String) {

    @PostConstruct
    private fun setWorkindDir() {
        CoreConfig.`workingDir_$eq`(workingDir)
    }
}