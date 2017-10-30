package com.example.codeChallenge.controllers;

import com.example.codeChallenge.Main;
import com.example.codeChallenge.readers.SparkJsonFileReader;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:/application-test.properties")
public class MainControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private SparkJsonFileReader sparkJsonFileReader;

    private List<String> stringList;

    @Before
    public void setup() {
        stringList = new ArrayList<>();
        stringList.add("{\"socialType\":\"INSTAGRAM_PICTURE\",\"socialId\":\"1453799147568385409_1428837659\",\"timestamp\":\"2017-02-19 17:46:25\",\"username\":\"officialasroma\",\"userId\":\"1428837659\",\"content\":\"#DajeRoma \uD83D\uDD36\uD83D\uDD34\\n***\\nFollow @officialasroma on Instagram\\n***\\n#ASRoma #Roma #Dzeko #Salah #momo #edin #bosnia #egypt #asromagoal #celebration #Rome #serieatim #seriea #strootman #preres #nainggolan\",\"latitude\":41.933872,\"longitude\":12.454714}");
        given(this.sparkJsonFileReader.
                getAll()
        ).willReturn(
                stringList);
    }

    /**
     * Tests all application controllers
     * 1) Availability?
     * 2) Return a Json?
     */
    @Test
    public void test() {
        ResponseEntity<String> forEntity = this.restTemplate.getForEntity("/",
                String.class);

        Assert.assertEquals(forEntity.getStatusCode().value(), HttpStatus.OK.value());
        Assert.assertEquals(forEntity.getHeaders().getContentType().toString(), ContentType.APPLICATION_JSON.toString().replace(" ", ""));

        forEntity = this.restTemplate.getForEntity("/0",
                String.class);

        Assert.assertEquals(forEntity.getStatusCode().value(), HttpStatus.OK.value());
        Assert.assertEquals(forEntity.getHeaders().getContentType().toString(), ContentType.APPLICATION_JSON.toString().replace(" ", ""));

        forEntity = this.restTemplate.getForEntity("/1",
                String.class);

        Assert.assertEquals(forEntity.getStatusCode().value(), HttpStatus.OK.value());
        Assert.assertEquals(forEntity.getHeaders().getContentType().toString(), ContentType.APPLICATION_JSON.toString().replace(" ", ""));

        forEntity = this.restTemplate.getForEntity("/2",
                String.class);

        Assert.assertEquals(forEntity.getStatusCode().value(), HttpStatus.OK.value());
        Assert.assertEquals(forEntity.getHeaders().getContentType().toString(), ContentType.APPLICATION_JSON.toString().replace(" ", ""));

        forEntity = this.restTemplate.getForEntity("/3",
                String.class);

        Assert.assertEquals(forEntity.getStatusCode().value(), HttpStatus.OK.value());
        Assert.assertEquals(forEntity.getHeaders().getContentType().toString(), ContentType.APPLICATION_JSON.toString().replace(" ", ""));

        forEntity = this.restTemplate.getForEntity("/4",
                String.class);

        Assert.assertEquals(forEntity.getStatusCode().value(), HttpStatus.OK.value());
        Assert.assertEquals(forEntity.getHeaders().getContentType().toString(), ContentType.APPLICATION_JSON.toString().replace(" ", ""));

        forEntity = this.restTemplate.getForEntity("/5",
                String.class);

        Assert.assertEquals(forEntity.getStatusCode().value(), HttpStatus.OK.value());
        Assert.assertEquals(forEntity.getHeaders().getContentType().toString(), ContentType.APPLICATION_JSON.toString().replace(" ", ""));

        forEntity = this.restTemplate.getForEntity("/6",
                String.class);

        Assert.assertEquals(forEntity.getStatusCode().value(), HttpStatus.OK.value());
        Assert.assertEquals(forEntity.getHeaders().getContentType().toString(), ContentType.APPLICATION_JSON.toString().replace(" ", ""));
    }
}
