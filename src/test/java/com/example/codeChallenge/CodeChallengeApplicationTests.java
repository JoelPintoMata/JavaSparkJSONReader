package com.example.codeChallenge;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class CodeChallengeApplicationTests {

	@Autowired
	SparkReader sparkReader;

    @Test
    public void contextLoads() {
        Assert.assertEquals(sparkReader.getAll().size(), 898);
    }

    @Test
    public void actor1() {
        Assert.assertEquals(sparkReader.getAll().size(), 898);
    }

    @Test
    public void actor2() {
        Assert.assertEquals(sparkReader.getAll().size(), 898);
    }

    /**
     * The total sum of a contains plus not contains should be equal to the total of records in the rdd
     */
    @Test
    public void containsPlusNotContains() {
        Assert.assertEquals(sparkReader.getByContains("disney").size() + sparkReader.getByNotContains("disney").size(), sparkReader.getAll().size());
    }
}
