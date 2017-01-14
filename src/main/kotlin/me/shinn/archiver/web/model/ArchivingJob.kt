package me.shinn.archiver.web.model

import java.util.concurrent.atomic.AtomicLong


data class ArchivingJob(val url: String, val id: String, var status: JobStatus) {
    companion object {
        val idCounter = AtomicLong()
    }

    constructor(url: String): this(url= url, id= idCounter.incrementAndGet().toString(), status= JobStatus.RUNNING)

    fun  end() {
        status = JobStatus.COMPLETED
    }

    fun  fail() {
        status = JobStatus.FAILED
    }

}
