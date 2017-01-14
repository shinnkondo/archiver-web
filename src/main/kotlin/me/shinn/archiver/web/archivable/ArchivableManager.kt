package me.shinn.archiver.web.archivable

import io.reactivex.Completable
import me.shinn.archiver.core.CoreManager
import me.shinn.archiver.core.config.Pwd
import me.shinn.archiver.web.model.ArchivingJob
import me.shinn.archiver.web.socket.ArchivableUpdateHandler
import org.jetbrains.annotations.Mutable
import org.springframework.beans.factory.annotation.Value
import java.util.concurrent.ConcurrentHashMap

import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.inject.Named

@Named
class ArchivableManager(@Inject val coreManager: CoreManager, @Inject val handler: ArchivableUpdateHandler) {
    val jobs: MutableMap<String, ArchivingJob> = ConcurrentHashMap()

    fun run(url: String): String {
        val c = coreManager.run(url)
        val a = ArchivingJob(url)
        jobs.put(a.id, a)
        handler.update()
        c.subscribe({
            a.end()
            handler.update()
        }, { e ->
            a.fail()
            handler.update()
            throw e
        })
        return a.id
    }

     fun queryStatus(jobId: String): String {
        return jobs[jobId]?.status.toString()
     }

    fun queryStatus() = jobs.values



}
