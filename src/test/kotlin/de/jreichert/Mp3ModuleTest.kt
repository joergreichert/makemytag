package de.jreichert

import com.mpatric.mp3agic.Mp3File
import de.jreichert.mp3.Mp3Service
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.extension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ExtendWith(SpringExtension::class)
@ActiveProfiles("dev")
class Mp3ModuleTest {

    @Autowired
    lateinit var mp3Service: Mp3Service

    @Test
    fun test() {
        val root = "/media/daten/Musik/"
        val visitor = object : SimpleFileVisitor<Path>() {

            override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
                println(file)
                if (file.extension == "mp3") {
                    mp3Service.storeMp3MetaData(file)
                }
                return FileVisitResult.CONTINUE;
            }
        }
        Files.walkFileTree(Paths.get(root), visitor)
    }
}