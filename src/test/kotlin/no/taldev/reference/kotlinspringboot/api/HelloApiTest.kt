package no.taldev.reference.kotlinspringboot.api

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(HelloApi::class)
class HelloApiTest(
        @Autowired val mvc: MockMvc
) {

    @Nested
    inner class GetHello {

        @Test
        fun `should return a message when called with no arguments`() {
            val result = mvc.perform(MockMvcRequestBuilders
                    .get("/hello"))
                    .andExpect(status().isOk)
                    .andReturn().response.contentAsString

            val expected = "{\"message\":\"Hello world\"}"

            Assertions.assertEquals(expected, result)
        }
    }
}