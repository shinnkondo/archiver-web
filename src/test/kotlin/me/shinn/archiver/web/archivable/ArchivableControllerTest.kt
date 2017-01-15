package me.shinn.archiver.web.archivable

import com.nhaarman.mockito_kotlin.whenever
import me.shinn.archiver.web.model.ArchivingJob
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ArchivableControllerTest : Spek({
    describe("a controller") {
        val mockedManager = mock(ArchivableManager::class.java)
        val controller = ArchivableController(mockedManager)
        on("list()") {
            whenever(mockedManager.queryStatus()).thenReturn(mutableListOf(ArchivingJob("http")))
            val result = controller.list()

            it("should query manager") {
                verify(mockedManager).queryStatus()
            }

            it("should return job status") {
                assert(result.first().url == "http")
                assert(result.first() is ArchivingJob)
            }
        }
    }
})