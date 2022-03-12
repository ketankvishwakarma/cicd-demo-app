package com.demo.cicdapp.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
 
@ExtendWith(SpringExtension.class)
@WebMvcTest(SimpleRestController.class)
public class SimpleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testHello() throws Exception {
        mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Hello!"));
    }

    @Test
    void testHelloSomethig() throws Exception {
        mockMvc.perform(get("/sweety"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Hello sweety!"));
    }
}
