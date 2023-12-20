package xyz.bnbong.bnbongservergateway.route.filter

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration

@Configuration
class GlobalRequestLoggingFilter {
    private val logger = LoggerFactory.getLogger(javaClass)
}