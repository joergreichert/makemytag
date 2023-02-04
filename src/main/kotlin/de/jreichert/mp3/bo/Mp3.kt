package de.jreichert.mp3.bo

import jakarta.persistence.*
import java.util.*

@Entity
data class Mp3(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(unique = true, nullable = false)
        val externalId: UUID = UUID.randomUUID(),
        @Column(unique = true, nullable = false)
        val absPath: String,
        val fileName: String? = null,
        val fileSizeInBytes: Long? = null,
        val lengthInSeconds: Long? = null,
        val bitrateInKbps: Int? = null,
        val variableBitrate: Boolean? = null,
        val sampleRateInHz: Int? = null,
        val track: String? = null,
        val artist: String? = null,
        val title: String? = null,
        val album: String? = null,
        val year: String? = null,
        val genre: String? = null,
        val genreDescription: String? = null,
        val comment: String? = null,
        val composer: String? = null,
        val publisher: String? = null,
        val originalArtist: String? = null,
        val albumArtist: String? = null,
        val copyright: String? = null,
        val url: String? = null,
        val encoder: String? = null
)