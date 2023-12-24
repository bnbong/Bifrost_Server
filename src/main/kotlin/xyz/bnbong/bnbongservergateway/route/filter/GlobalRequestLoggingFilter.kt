package xyz.bnbong.bnbongservergateway.route.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.server.ServerWebExchange
import xyz.bnbong.bnbongservergateway.utils.TemplateLogger

@Configuration
class GlobalRequestLoggingFilter {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun postGlobalFilter(): GlobalFilter? {
        return GlobalFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request
            val requestLog = TemplateLogger.createRequestLog(request)
            logger.info(requestLog)

            chain.filter(exchange)
        }
    }
}