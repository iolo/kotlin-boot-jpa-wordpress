package kr.iolo.samples

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(properties = arrayOf("application-test.properties"))
class AppTest {

    @Autowired
    var app: App? = null

    @Test
    fun testBoot() {
        assertNotNull(app)
    }

}
