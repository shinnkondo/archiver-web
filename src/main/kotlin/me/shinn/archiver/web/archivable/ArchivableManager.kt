package me.shinn.archiver.web.archivable

import me.shinn.archiver.web.model.ArchivingJob

interface ArchivableManager {
    fun run(url: String): String

    fun queryStatus(jobId: String): String

    fun queryStatus(): Collection<ArchivingJob>
}