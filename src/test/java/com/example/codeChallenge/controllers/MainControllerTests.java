package com.example.codeChallenge.controllers;

import com.example.codeChallenge.CodeChallengeApplication;
import com.example.codeChallenge.readers.SparkReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = CodeChallengeApplication.class)
})
@TestPropertySource(locations = "classpath:/application-test.properties")
public class MainControllerTests {

    @Autowired
    @Qualifier("sparkJsonFileReader")
    SparkReader sparkReader;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new MainController()).build();
    }

    @Test
    public void testSayHelloWorld() throws Exception {
        this.mockMvc.perform(get("/")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}
