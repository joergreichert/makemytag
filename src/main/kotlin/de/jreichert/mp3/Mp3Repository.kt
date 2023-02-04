package de.jreichert.mp3

import de.jreichert.mp3.bo.Mp3
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Mp3Repository : JpaRepository<Mp3, Long> {

    fun findByAbsPath(absPath: String): List<Mp3>
}