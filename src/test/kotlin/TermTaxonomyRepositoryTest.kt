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
open class TermTaxonomyRepositoryTest {
    @Autowired
    var repo: TermTaxonomyRepository? = null

    @Test
    @Transactional
    fun testFindAll() {
        assertNotNull(repo)
        val res = repo?.findAll()!!
        assertEquals(4, res.size)
        res.forEachIndexed { i, it ->
            println(it)
            assertEquals(i + 1L, it.id)
            println(it.term)
            println(it.relationships)
            when (it.id) {
                1L -> assertEquals(3, it.relationships.size)
                2L -> assertEquals(2, it.relationships.size)
                3L -> assertEquals(1, it.relationships.size)
                4L -> assertEquals(1, it.relationships.size)
            }
        }
    }
}
//INSERT INTO wp_term_relationships
//(object_id, term_taxonomy_id, term_order)
//VALUES
//(1, 4, 14),
//(1, 3, 13),
//(1, 2, 12),
//(2, 2, 22),
//(2, 1, 21),
//(3, 1, 31),
//(4, 1, 41);
