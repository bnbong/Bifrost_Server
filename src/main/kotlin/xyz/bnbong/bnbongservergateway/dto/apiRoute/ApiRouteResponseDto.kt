package xyz.bnbong.bnbongservergateway.dto.apiRoute

import xyz.bnbong.bnbongservergateway.domain.apiRoute.ApiRoute


data class ApiRouteResponseDto(
    val id: Int,
    val path: String,
    val methodName: String,
){
    constructor(apiRoute: ApiRoute):this(
        id = apiRoute.id,
        path = apiRoute.path,
        methodName = apiRoute.method.name,
    )
}