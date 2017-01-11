package me.shinn.web

import io.reactivex.Completable
import me.shinn.download.DownloadManager
import me.shinn.download.config.Pwd
import org.springframework.beans.factory.annotation.Value

import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.inject.Named

@Named
class ArchivableManager {

    @Inject
    DownloadManager downloadManager

    @Inject
    ArchivableUpdateHandler handler

    @Value('${me.shinn.workingDir}')
    String workingDir

    Map<String, ArchivingJob> jobs = [:].asSynchronized()

    String run(String url) {
        Completable c = downloadManager.run(url)
        def a = ArchivingJob.create(url)
        jobs[a.id] = a
        c.subscribe({
            handler.update()
            a.end()
        }, {
            a.fail()
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
