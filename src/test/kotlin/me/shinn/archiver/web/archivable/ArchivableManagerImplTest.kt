package me.shinn.archiver.web.archivable

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import me.shinn.archiver.core.CoreManager
import me.shinn.archiver.web.socket.ArchivingJobUpdateNotifier
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal class ArchivableManagerImplTest : Spek({
    describe("archival manager impl") {
        val core: CoreManager = mock()
        val handler: ArchivingJobUpdateNotifier = mock()
        val manager = ArchivableManagerImpl(core, handler)
        on("run") {
            whenever(core.run("url")).thenReturn(Flowable.empty())
            val result = manager.run("url")

            it("should call core run") {
                verify(core).run("url")
            }

            it("should return new id") {
                assert(result == "1")
            }

            it("should update notifier") {
                verify(handler, times(2)).update()
            }
        }

        on("queryStatus single") {

        }

        on("queryStatus list") {

        }
    }
})