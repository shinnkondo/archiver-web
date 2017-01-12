package me.shinn.archiver.web.archivable

import io.reactivex.Completable
import me.shinn.archiver.core.CoreManager
import me.shinn.archiver.core.config.Pwd
import me.shinn.archiver.web.model.ArchivingJob
import me.shinn.archiver.web.socket.ArchivableUpdateHandler
import org.springframework.beans.factory.annotation.Value

import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.inject.Named

@Named
class ArchivableManager {

    @Inject
    CoreManager coreManager

    @Inject
    ArchivableUpdateHandler handler

    @Value('${me.shinn.workingDir}')
    String workingDir

    Map<String, ArchivingJob> jobs = [:].asSynchronized()

    String run(String url) {
        Completable c = coreManager.run(url)
        def a = ArchivingJob.create(url)
        jobs[a.id] = a
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

    String queryStatus(String jobId) {
        return jobs[jobId].status
    }

    Collection<ArchivingJob> queryStatus() {
        return jobs.values()
    }

    @PostConstruct
    private setWorkindDir() {
        Pwd.set(workingDir)
    }

}
