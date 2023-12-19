package xyz.bnbong.bnbongservergateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BnbongServerGatewayApplication

fun main(args: Array<String>) {
    runApplication<BnbongServerGatewayApplication>(*args)
}
