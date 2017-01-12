package me.shinn.archiver.web.model

import java.util.concurrent.atomic.AtomicLong


class ArchivingJob {
    String url
    String id
    JobStatus status

    private static AtomicLong idCounter = new AtomicLong()

    static create(url) {
        new ArchivingJob(url: url, id: String.valueOf(idCounter.incrementAndGet()), status: JobStatus.RUNNING)
    }

    synchronized  end() {
        status = JobStatus.COMPLETED
    }

    synchronized  fail() {
        status = JobStatus.FAILED
    }

}
