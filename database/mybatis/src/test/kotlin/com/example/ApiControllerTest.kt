package com.example

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper
    @MockBean
    lateinit var userMapper: UserMapper

    @Test
    fun findAllTest() {
        val list = listOf(
                User(1, "name1", LocalDateTime.now(), LocalDateTime.now()),
                User(2, "name2", LocalDateTime.now(), LocalDateTime.now()))
        `when`(userMapper.findAll()).thenReturn(list)

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk)
                .andExpect(content().json(objectMapper.writeValueAsString(list)))
    }
}
