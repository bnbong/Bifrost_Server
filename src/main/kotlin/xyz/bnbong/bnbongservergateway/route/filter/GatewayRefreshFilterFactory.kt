package xyz.bnbong.bnbongservergateway.route.filter

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory
import org.springframework.stereotype.Component
import xyz.bnbong.bnbongservergateway.utils.GatewayEventPublisher

@Component
class GatewayRefreshFilterFactory(
    @Autowired val gatewayEventPublisher: GatewayEventPublisher
)  : GatewayFilterFactory<GatewayRefreshFilterFactory.Config> {

    private val logger = LoggerFactory.getLogger(javaClass)

    data class Config(
        var isEnable: Boolean? = true,
    )

    override fun newConfig(): Config {
        return Config()
    }

    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            logger.info("GatewayRefreshed")
            gatewayEventPublisher.refreshRoute()

            chain.filter(exchange)
        }

    }
}