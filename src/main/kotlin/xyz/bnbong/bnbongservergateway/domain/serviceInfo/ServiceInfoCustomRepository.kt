package xyz.bnbong.bnbongservergateway.domain.serviceInfo

import reactor.core.publisher.Mono

interface ServiceInfoCustomRepository {
    fun findServiceById(id: Int): Mono<ServiceInfo>
    fun findAllService(): Mono<List<ServiceInfo>>
}