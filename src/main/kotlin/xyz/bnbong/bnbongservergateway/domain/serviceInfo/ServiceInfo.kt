// TODO: 나머지 Service Impl 클래스 추가.
package xyz.bnbong.bnbongservergateway.domain.serviceInfo

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "SERVICE")
data class ServiceInfo (
    @Id
    @Column("SERVICE_PK")
    val id: Int,

    @Column("SERVICE_NM")
    val name: String,

    @Column("SERVICE_DOMAIN")
    val domain: String,

    @Column("SERVICE_INDEX")
    val index: String? = null
)