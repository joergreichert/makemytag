package de.jreichert

import com.mpatric.mp3agic.Mp3File
import org.junit.jupiter.api.Test
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.extension

class Mp3TagsTest {

    @Test
    fun test() {
        val root = "/media/daten/Musik/"
        val visitor = object : SimpleFileVisitor<Path>() {

            override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
                println(file)
                if (file.extension == "mp3") {
                    processFile(file)
                }
                return FileVisitResult.CONTINUE;
            }
        }
        Files.walkFileTree(Paths.get(root), visitor)
    }

    private fun processFile(path: Path) {
        val mp3file = Mp3File(path)
        // println("Length of this mp3 is: " + mp3file.lengthInSeconds + " seconds")
        // println("Bitrate: " + mp3file.bitrate + " kbps " + if (mp3file.isVbr()) "(VBR)" else "(CBR)")
        // println("Sample rate: " + mp3file.sampleRate + " Hz")
        // println("Has ID3v1 tag?: " + if (mp3file.hasId3v1Tag()) "YES" else "NO")
        // println("Has ID3v2 tag?: " + if (mp3file.hasId3v2Tag()) "YES" else "NO")
        // println("Has custom tag?: " + if (mp3file.hasCustomTag()) "YES" else "NO")

        if (mp3file.hasId3v2Tag()) {
            val id3v2Tag = mp3file.id3v2Tag
            if (id3v2Tag.artist != "Erik Satie") {
                return
            }
            println("Track: " + id3v2Tag.track)
            println("Artist: " + id3v2Tag.artist)
            println("Title: " + id3v2Tag.title)
            println("Album: " + id3v2Tag.album)
            println("Year: " + id3v2Tag.year)
            println("Genre: " + id3v2Tag.genre + " (" + id3v2Tag.genreDescription + ")")
            println("Comment: " + id3v2Tag.comment)
            println("Composer: " + id3v2Tag.composer)
            println("Publisher: " + id3v2Tag.publisher)
            println("Original artist: " + id3v2Tag.originalArtist)
            println("Album artist: " + id3v2Tag.albumArtist)
            println("Copyright: " + id3v2Tag.copyright)
            println("URL: " + id3v2Tag.url)
            println("Encoder: " + id3v2Tag.encoder)
        }
    }
}