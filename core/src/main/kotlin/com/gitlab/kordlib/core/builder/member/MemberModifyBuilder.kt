package com.gitlab.kordlib.core.builder.member

import com.gitlab.kordlib.core.builder.AuditRequestBuilder
import com.gitlab.kordlib.core.builder.KordDsl
import com.gitlab.kordlib.core.entity.Snowflake
import com.gitlab.kordlib.rest.json.request.GuildMemberModifyRequest

@KordDsl
class MemberModifyBuilder : AuditRequestBuilder<GuildMemberModifyRequest> {
    override var reason: String? = null
    var voiceChannelId: Snowflake? = null
    var muted: Boolean? = null
    var deafened: Boolean? = null
    var nickname: String? = null
    val roles: MutableSet<Snowflake> = mutableSetOf()

    override fun toRequest(): GuildMemberModifyRequest = GuildMemberModifyRequest(
            nick = nickname,
            channelId = voiceChannelId?.value,
            mute = muted,
            deaf = deafened,
            roles = roles.map { it.value }
    )
}