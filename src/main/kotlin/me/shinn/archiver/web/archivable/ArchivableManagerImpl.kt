package me.shinn.archiver.web.archivable

import io.reactivex.exceptions.CompositeException
import me.shinn.archiver.core.CoreManager
import me.shinn.archiver.web.model.ArchivingJob
import me.shinn.archiver.web.socket.ArchivingJobUpdateNotifier
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Named

@Named
class ArchivableManagerImpl(val coreManager: CoreManager, val notifier: ArchivingJobUpdateNotifier) : ArchivableManager {
    val jobs: MutableMap<String, ArchivingJob> = ConcurrentHashMap()

    override fun run(url: String): String {
        val c = coreManager.run(url)
        val a = ArchivingJob(url)
        jobs.put(a.id, a)
        notifier.update()
        c.subscribe({ title ->
            a.addMessage("$title success")
        }, { e ->
            if (e is CompositeException) {
                a.fail("${e.getExceptions()}")
            } else {
                a.fail(e.toString())
            }
            notifier.update()
        }, {
            a.end()
            notifier.update()
        })
        return a.id
    }

    override fun queryStatus(jobId: String): String {
        return jobs[jobId]?.status.toString()
    }

    override fun queryStatus() = jobs.values


}
