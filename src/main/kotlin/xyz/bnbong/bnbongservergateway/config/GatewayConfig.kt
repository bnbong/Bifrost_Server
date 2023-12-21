package xyz.bnbong.bnbongservergateway.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.filter.factory.SetPathGatewayFilterFactory
import xyz.bnbong.bnbongservergateway.route.RouteLocatorImpl
import xyz.bnbong.bnbongservergateway.route.filter.GatewayRefreshFilterFactory
import xyz.bnbong.bnbongservergateway.service.ApiRouteService

@Configuration
class GatewayConfig {
    @Bean
    fun routeLocator(
        apiRouteService: ApiRouteService,
        routeLocatorBuilder: RouteLocatorBuilder,
        setPathGatewayFilterFactory: SetPathGatewayFilterFactory,
        gatewayRefreshFilterFactory: GatewayRefreshFilterFactory
    ) : RouteLocator {
        return RouteLocatorImpl(
            apiRouteService = apiRouteService,
            routeLocatorBuilder = routeLocatorBuilder,
            setPathGatewayFilterFactory = setPathGatewayFilterFactory,
            gatewayRefreshFilterFactory = gatewayRefreshFilterFactory
        )
    }
}