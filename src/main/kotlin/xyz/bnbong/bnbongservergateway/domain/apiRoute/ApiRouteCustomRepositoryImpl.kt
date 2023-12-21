package xyz.bnbong.bnbongservergateway.domain.apiRoute

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.r2dbc.core.DatabaseClient
import reactor.core.publisher.Flux

class ApiRouteCustomRepositoryImpl(
    @Autowired val client: DatabaseClient,
    @Autowired val apiRouteMapper: ApiRouteMapper
) : ApiRouteCustomRepository {

    override fun findAllService(): Flux<ApiRoute> {
        val query = """
               SELECT * FROM API_ROUTE
               LEFT JOIN METHOD
               ON METHOD.METHOD_PK = API_ROUTE.METHOD_METHOD_PK
               LEFT JOIN SERVICE
               ON SERVICE.SERVICE_PK = API_ROUTE.SERVICE_SERVICE_PK
               """.trimIndent()
        return client.sql(query)
            .map(apiRouteMapper)
            .all()
    }

    override fun findAllOrderServiceAndPriority(): Flux<ApiRoute> {
        val query = """
               SELECT * FROM API_ROUTE
               LEFT JOIN METHOD
               ON METHOD.METHOD_PK = API_ROUTE.METHOD_METHOD_PK
               LEFT JOIN SERVICE
               ON SERVICE.SERVICE_PK = API_ROUTE.SERVICE_SERVICE_PK
               ORDER BY API_ROUTE.SERVICE_SERVICE_PK ASC, API_ROUTE.PRIORITY ASC
               """.trimIndent()
        return client.sql(query)
            .map(apiRouteMapper)
            .all()
    }
}