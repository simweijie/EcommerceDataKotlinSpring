package com.okta.kotlin

import com.okta.kotlin.model.DataSetModel
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer

@Configuration
open class RestConfiguration : RepositoryRestConfigurer {
    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?) {
        config?.exposeIdsFor(CoffeeShopModel::class.java)
        config?.exposeIdsFor(DataSetModel::class.java)
        config?.setBasePath("/api");
    }
}