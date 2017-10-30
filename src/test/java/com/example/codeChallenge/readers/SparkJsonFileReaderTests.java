package com.example.codeChallenge.readers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class SparkJsonFileReaderTests {

	@Autowired
    @Qualifier("sparkJsonFileReader")
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

    /**
     * The total sum of a records before, equal and after a date should be equal to the total of records in the rdd
     */
    @Test
    public void beforeEqualAfterDate() {
        Assert.assertEquals(sparkReader.getBeforeDate(LocalDate.of(2017, Month.FEBRUARY, 1)).size()
                + sparkReader.getEqualsDate(LocalDate.of(2017, Month.FEBRUARY, 1)).size()
                + sparkReader.getAfterDate(LocalDate.of(2017, Month.FEBRUARY, 1)).size()
        , sparkReader.getAll().size());
    }
}
