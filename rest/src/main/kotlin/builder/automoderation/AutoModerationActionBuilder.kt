package dev.kord.rest.builder.automoderation

import dev.kord.common.annotation.KordDsl
import dev.kord.common.entity.AutoModerationActionType
import dev.kord.common.entity.AutoModerationActionType.*
import dev.kord.common.entity.DiscordAutoModerationAction
import dev.kord.common.entity.DiscordAutoModerationActionMetadata
import dev.kord.common.entity.Snowflake
import dev.kord.common.entity.optional.Optional
import dev.kord.common.entity.optional.optional
import dev.kord.common.entity.optional.optionalSnowflake
import dev.kord.rest.builder.RequestBuilder
import kotlin.time.Duration

/**
 * A [RequestBuilder] for building [actions][DiscordAutoModerationAction] which will execute whenever a
 * [rule][AutoModerationRuleBuilder] is triggered.
 */
@KordDsl
public sealed class AutoModerationActionBuilder : RequestBuilder<DiscordAutoModerationAction> {

    /** The type of action. */
    public abstract val type: AutoModerationActionType

    protected open fun buildMetadata(): Optional<DiscordAutoModerationActionMetadata> = Optional.Missing()

    final override fun toRequest(): DiscordAutoModerationAction = DiscordAutoModerationAction(
        type = type,
        metadata = buildMetadata(),
    )
}

/** An [AutoModerationActionBuilder] for building actions with type [BlockMessage]. */
@Suppress("CanSealedSubClassBeObject") // keep it as a class in case we want to add more in the future
@KordDsl
public class BlockMessageAutoModerationActionBuilder : AutoModerationActionBuilder() {
    override val type: BlockMessage get() = BlockMessage
}

/** An [AutoModerationActionBuilder] for building actions with type [SendAlertMessage]. */
@KordDsl
public class SendAlertMessageAutoModerationActionBuilder(
    /** The ID of the channel to which user content should be logged. */
    public var channelId: Snowflake,
) : AutoModerationActionBuilder() {

    override val type: SendAlertMessage get() = SendAlertMessage

    override fun buildMetadata(): Optional.Value<DiscordAutoModerationActionMetadata> =
        DiscordAutoModerationActionMetadata(channelId = channelId.optionalSnowflake()).optional()
}

/** An [AutoModerationActionBuilder] for building actions with type [Timeout]. */
@KordDsl
public class TimeoutAutoModerationActionBuilder(
    /** The timeout duration (maximum of 2419200 seconds (4 weeks)). */
    public var duration: Duration,
) : AutoModerationActionBuilder() {

    override val type: Timeout get() = Timeout

    override fun buildMetadata(): Optional.Value<DiscordAutoModerationActionMetadata> =
        DiscordAutoModerationActionMetadata(durationSeconds = duration.optional()).optional()
}
