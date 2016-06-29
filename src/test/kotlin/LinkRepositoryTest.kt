package kr.iolo.samples;

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author iolo
 */
@RunWith(SpringRunner::class)
@SpringBootTest(properties = arrayOf("application-test.properties"))
class LinkRepositoryTest {
    @Autowired
    var repo: LinkRepository? = null

    @Test
    fun testFindAll() {
        assertNotNull(repo)
        val res = repo?.findAll()!!
        println(res)
        assertEquals(4, res.size)
        assertEquals(1, res[0].id)
        assertEquals(2, res[1].id)
        assertEquals(3, res[2].id)
        assertEquals(4, res[3].id)
    }
}
