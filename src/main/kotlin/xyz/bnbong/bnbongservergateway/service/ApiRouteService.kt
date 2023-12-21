package xyz.bnbong.bnbongservergateway.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import xyz.bnbong.bnbongservergateway.domain.apiRoute.ApiRouteRepository
import xyz.bnbong.bnbongservergateway.dto.apiRoute.ApiRouteResponseDto

@Service
class ApiRouteService(
    val routeRepository: ApiRouteRepository,
) {
    @Transactional(readOnly = true)
    fun findAllRoute(): Flux<ApiRouteResponseDto> {
        return routeRepository.findAllOrderServiceAndPriority()
            .map { route -> ApiRouteResponseDto(route) }
    }
}