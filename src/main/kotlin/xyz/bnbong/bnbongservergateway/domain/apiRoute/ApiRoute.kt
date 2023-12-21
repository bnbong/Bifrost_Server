package xyz.bnbong.bnbongservergateway.domain.apiRoute

import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import xyz.bnbong.bnbongservergateway.domain.method.Method

@Table(name = "API_ROUTE")
data class ApiRoute(
    @Id
    @Column("API_ROUTE_PK")
    val id: Int,

    @Column("API_ROUTE_PATH")
    val path: String,

    @Column("PRIORITY")
    val priority: Int,

    @Transient
    val method: Method,
)