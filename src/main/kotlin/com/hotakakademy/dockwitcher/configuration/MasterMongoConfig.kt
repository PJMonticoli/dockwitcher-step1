package com.hotakakademy.dockwitcher.configuration

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

// @Configuration: Indica que esta clase es una clase de configuración de Spring.
@Configuration

// @EnableMongoRepositories: Habilita la creación de repositorios de MongoDB en el
// paquete especificado y configura el mongoTemplateRef para que use mongoMasterTemplate

// @Bean objeto administrado por el contenedor de Spring,facilita la inyección de dependencias y la gestión de los objetos en la aplicación

@EnableMongoRepositories(basePackages = ["com.hotakakademy.dockwitcher.domain.repositories"], mongoTemplateRef = "mongoMasterTemplate")
class MasterMongoConfig {
    @Value("\${mongodb.core.database}")
    private val database: String? = null

    @Primary
    @Bean(name = ["mongoMasterTemplate"])
    fun mongoTemplate(@Qualifier("mongoMasterFactory") mongoClient: MongoClient?): MongoTemplate {
        return MongoTemplate(mongoClient!!, database!!)
    }

    @Primary
    @Bean("mongoMasterFactory")
    fun mongoClient(@Qualifier("mongoMasterProperties") mongoProperties: MongoProperties): MongoClient {
        return MongoClients.create(mongoProperties.uri)
    }

    @Bean(name = ["mongoMasterProperties"])
    @ConfigurationProperties(prefix = "mongodb.core")
    @Primary
    fun properties(): MongoProperties {
        return MongoProperties()
    }
}
