// THIS FILE IS AUTO-GENERATED BY KordEnumProcessor.kt, DO NOT EDIT!
@file:Suppress(names = arrayOf("RedundantVisibilityModifier", "IncorrectFormatting",
                "ReplaceArrayOfWithLiteral", "SpellCheckingInspection", "GrazieInspection"))

package dev.kord.common.entity

import kotlin.Any
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.Int
import kotlin.LazyThreadSafetyMode.PUBLICATION
import kotlin.ReplaceWith
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.jvm.JvmField
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * See [IntegrationExpireBehavior]s in the
 * [Discord Developer Documentation](https://discord.com/developers/docs/resources/guild#integration-object-integration-expire-behaviors).
 */
@Serializable(with = IntegrationExpireBehavior.NewSerializer::class)
public sealed class IntegrationExpireBehavior(
    /**
     * The raw value used by Discord.
     */
    public val `value`: Int,
) {
    public final override fun equals(other: Any?): Boolean = this === other ||
            (other is IntegrationExpireBehavior && this.value == other.value)

    public final override fun hashCode(): Int = value.hashCode()

    public final override fun toString(): String =
            "IntegrationExpireBehavior.${this::class.simpleName}(value=$value)"

    /**
     * An unknown [IntegrationExpireBehavior].
     *
     * This is used as a fallback for [IntegrationExpireBehavior]s that haven't been added to Kord
     * yet.
     */
    public class Unknown(
        `value`: Int,
    ) : IntegrationExpireBehavior(value)

    public object RemoveRole : IntegrationExpireBehavior(0)

    public object Kick : IntegrationExpireBehavior(1)

    internal object NewSerializer : KSerializer<IntegrationExpireBehavior> {
        public override val descriptor: SerialDescriptor =
                PrimitiveSerialDescriptor("dev.kord.common.entity.IntegrationExpireBehavior",
                PrimitiveKind.INT)

        public override fun serialize(encoder: Encoder, `value`: IntegrationExpireBehavior) =
                encoder.encodeInt(value.value)

        public override fun deserialize(decoder: Decoder) = when (val value = decoder.decodeInt()) {
            0 -> RemoveRole
            1 -> Kick
            else -> Unknown(value)
        }
    }

    @Deprecated(
        message = "Use 'IntegrationExpireBehavior.serializer()' instead.",
        replaceWith = ReplaceWith(expression = "IntegrationExpireBehavior.serializer()", imports =
                    arrayOf("dev.kord.common.entity.IntegrationExpireBehavior")),
    )
    public object Serializer : KSerializer<IntegrationExpireBehavior> by NewSerializer {
        @Deprecated(
            message = "Use 'IntegrationExpireBehavior.serializer()' instead.",
            replaceWith = ReplaceWith(expression = "IntegrationExpireBehavior.serializer()", imports
                        = arrayOf("dev.kord.common.entity.IntegrationExpireBehavior")),
        )
        public fun serializer(): KSerializer<IntegrationExpireBehavior> = this
    }

    public companion object {
        /**
         * A [List] of all known [IntegrationExpireBehavior]s.
         */
        public val entries: List<IntegrationExpireBehavior> by lazy(mode = PUBLICATION) {
            listOf(
                RemoveRole,
                Kick,
            )
        }


        @Suppress(names = arrayOf("DEPRECATION"))
        @Deprecated(
            level = DeprecationLevel.HIDDEN,
            message = "Binary compatibility",
        )
        @JvmField
        public val Serializer: Serializer = Serializer
    }
}
