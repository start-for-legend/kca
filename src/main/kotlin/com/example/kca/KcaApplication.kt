package com.example.kca

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class KcaApplication

fun main(args: Array<String>) {
	runApplication<KcaApplication>(*args)
}
