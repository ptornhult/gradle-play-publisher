package com.github.triplet.gradle.play.internal

import com.github.triplet.gradle.play.PlayPublisherExtension

internal val PlayPublisherExtension.trackOrDefault get() = _track ?: TrackType.INTERNAL
internal val PlayPublisherExtension.resolutionStrategyOrDefault
    get() = _resolutionStrategy ?: ResolutionStrategy.FAIL

internal fun PlayPublisherExtension?.mergeWith(
        default: PlayPublisherExtension
): PlayPublisherExtension {
    if (this == null) return default

    PlayPublisherExtension::class.java.declaredFields.filter {
        it.name.startsWith("_")
    }.forEach {
        it.isAccessible = true
        if (it[this] == null) it[this] = it[default]
    }

    return this
}
