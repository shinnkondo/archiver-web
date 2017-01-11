package me.shinn.web

import com.google.inject.Guice
import com.google.inject.Injector
import me.shinn.download.DownloadManager
import me.shinn.download.config.PhotoUtilModule
import me.shinn.download.site.WebSiteModule
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
        Injector injector = Guice.createInjector(new PhotoUtilModule(), new WebSiteModule())
        return injector.getInstance(DownloadManager.class)
    }
}
