package xyz.bnbong.bnbongservergateway.route

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.filter.factory.SetPathGatewayFilterFactory
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.Buildable
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import reactor.core.publisher.Flux
import xyz.bnbong.bnbongservergateway.dto.apiRoute.ApiRouteResponseDto
import xyz.bnbong.bnbongservergateway.route.filter.GatewayRefreshFilterFactory
import xyz.bnbong.bnbongservergateway.service.ApiRouteService

class RouteLocatorImpl(
    @Autowired val apiRouteService: ApiRouteService,
    @Autowired val routeLocatorBuilder: RouteLocatorBuilder,
    @Autowired val setPathGatewayFilterFactory: SetPathGatewayFilterFactory,
    @Autowired val gatewayRefreshFilterFactory: GatewayRefreshFilterFactory,
) : RouteLocator {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun getRoutes(): Flux<Route> {
        logger.info("start setup routes.")

        val routesBuilder = routeLocatorBuilder.routes()

        return apiRouteService.findAllRoute()
            .map { route ->
                routesBuilder.route(route.id.toString())
                { predicateSpec -> setPredicateSpec(route, predicateSpec) }
            }
            .collectList()
            .flatMapMany { routesBuilder.build().routes }
    }
    private fun setPredicateSpec(route: ApiRouteResponseDto, predicateSpec: PredicateSpec): Buildable<Route?> {

        logger.info("SET {} : {}", route.methodName, route.path)
        //set route path. ex. /api/v1/member/{id} ...
        val booleanSpec = predicateSpec.path(route.path)

        //set http method. ex. GET, POST, PUT, DELETE etc.
        booleanSpec.and().method(route.methodName)


        if (route.path == "/api/v1/refresh/**") {
            booleanSpec.filters {
                it.filters(
                    gatewayRefreshFilterFactory.apply { config: GatewayRefreshFilterFactory.Config ->
                        config.isEnable = true
                    })
            }
        }

        //set domain and return route
        return booleanSpec.uri(route.serviceDomain)
    }
}