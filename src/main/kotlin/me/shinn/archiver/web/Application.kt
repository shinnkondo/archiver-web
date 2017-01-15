package me.shinn.archiver.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import me.shinn.archiver.core.CoreManagerImpl
import me.shinn.archiver.core.config.ModuleBundle
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Bean
    fun coreManager() = ModuleBundle.injector.getInstance(CoreManagerImpl::class.java)

    @Bean
    fun objectMapper() = ObjectMapper().registerModule(KotlinModule())

//    For ordinary classes, annotation based configuration is used since it involves less typing for constructor based DI.
}
fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}