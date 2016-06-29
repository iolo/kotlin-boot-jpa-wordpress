package kr.iolo.samples;

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.transaction.Transactional

/**
 * @author iolo
 */
@RunWith(SpringRunner::class)
@SpringBootTest(properties = arrayOf("application-test.properties"))
// XXX: lazy fetch -> @Transactional -> cglib proxy -> **open** class
open class PostRepositoryTest {
    @Autowired
    var repo: PostRepository? = null

    @Test
    @Transactional
    fun testFindAll() {
        assertNotNull(repo)
        val res = repo?.findAll()!!
        assertEquals(4, res.size)
        res.forEachIndexed { i, it ->
            println(it)
            assertEquals(i + 1L, it.id)
            println(it.parent)
            println(it.metas)
            println(it.comments)
            println(it.author)
        }
    }
}
