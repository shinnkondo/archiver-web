package me.shinn.archiver.web

import me.shinn.archiver.core.CoreManager
import me.shinn.archiver.core.config.ModuleBundle
import me.shinn.archiver.web.archivable.ArchivableManager
import me.shinn.archiver.web.socket.ArchivableUpdateHandler
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ArchivableManager archivableManager() {
        return new ArchivableManager()
    }

    @Bean
    CoreManager coreManager() {
        return ModuleBundle.injector.getInstance(CoreManager.class)
    }

    @Bean
    ArchivableUpdateHandler archivableUpdateHandler() {
        return new ArchivableUpdateHandler()
    }
}
