package me.shinn.web

import io.reactivex.Completable
import me.shinn.download.DownloadManager

import javax.inject.Inject
import javax.inject.Named

@Named
class ArchivableManager {

    @Inject
    DownloadManager downloadManager

    Map<String, ArchivingJob> jobs = [:].asSynchronized()

    String run(String url) {
        Completable c = downloadManager.run(url)
        def a = ArchivingJob.create(url)
        jobs[a.id] = a
        c.subscribe({
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

}
