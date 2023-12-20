package xyz.bnbong.bnbongservergateway.route

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import reactor.core.publisher.Flux

class RouteLocatorImpl() : RouteLocator {
    override fun getRoutes(): Flux<Route> {
        TODO("Not yet implemented")
    }
}