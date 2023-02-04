package de.jreichert.mp3

import com.mpatric.mp3agic.Mp3File
import de.jreichert.mp3.bo.Mp3
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.stereotype.Service
import java.nio.file.Path
import kotlin.io.path.absolutePathString

@Service
class Mp3Service @ConstructorBinding constructor(internal val repository: Mp3Repository) {

    fun storeMp3MetaData(path: Path) {
        try {
            val existing = repository.findByAbsPath(path.absolutePathString())
            if (existing.isNotEmpty()) {
                return
            }
            val mp3file = Mp3File(path)
            if (mp3file.hasId3v2Tag()) {
                val id3v2Tag = mp3file.id3v2Tag

                val mp3 = Mp3(
                        absPath = path.absolutePathString(),
                        fileName = path.fileName.toString(),
                        fileSizeInBytes = path.toFile().totalSpace,
                        lengthInSeconds = mp3file.lengthInSeconds,
                        bitrateInKbps = mp3file.bitrate,
                        variableBitrate = mp3file.isVbr(),
                        sampleRateInHz = mp3file.sampleRate,
                        track = id3v2Tag.track,
                        artist = id3v2Tag.track,
                        title = id3v2Tag.track,
                        album = id3v2Tag.track,
                        year = id3v2Tag.track,
                        genre = id3v2Tag.track,
                        genreDescription = id3v2Tag.track,
                        comment = id3v2Tag.track,
                        composer = id3v2Tag.track,
                        publisher = id3v2Tag.track,
                        originalArtist = id3v2Tag.track,
                        albumArtist = id3v2Tag.track,
                        copyright = id3v2Tag.track,
                        url = id3v2Tag.track,
                        encoder = id3v2Tag.track
                )
                repository.save(mp3)
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
}