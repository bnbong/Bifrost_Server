package xyz.bnbong.bnbongservergateway.domain.apiRoute

import reactor.core.publisher.Flux

interface ApiRouteCustomRepository {
    fun findAllService(): Flux<ApiRoute>
    fun findAllOrderServiceAndPriority(): Flux<ApiRoute>
}