package me.shinn.archiver.web

import me.shinn.archiver.web.archivable.ArchivableManager
import me.shinn.download.DownloadManager
import me.shinn.download.config.ModuleBundle
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
    DownloadManager downloadManager() {
        return ModuleBundle.injector.getInstance(DownloadManager.class)
    }
}
