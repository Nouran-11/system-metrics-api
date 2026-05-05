package com.nouran

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    routing {
        get("/health") {
            call.respondText("UP")
        }

        get("/metrics") {
            val runtime = Runtime.getRuntime()

            call.respond(
                mapOf(
                    "freeMemory" to runtime.freeMemory(),
                    "totalMemory" to runtime.totalMemory(),
                    "maxMemory" to runtime.maxMemory(),
                    "availableProcessors" to runtime.availableProcessors()
                )
            )
        }
    }
}