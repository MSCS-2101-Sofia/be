package org.tennismate.com

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaRepositories(value = ["org.tennismate.com"])
@EntityScan(value = ["org.tennismate.com"])
open class Application

fun main(args: Array<String>) {
    SpringApplicationBuilder(Application::class.java).web(WebApplicationType.SERVLET).run(*args)
}