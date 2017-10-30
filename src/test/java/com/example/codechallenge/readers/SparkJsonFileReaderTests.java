package com.example.codechallenge.readers;

import com.example.codechallenge.enums.SocialTypeEnum;
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
    private SparkReader sparkReader;

    /**
     * Tests that all records are read correctly from the source
     */
    @Test
    public void getAll() {
        Assert.assertEquals(sparkReader.getAll().size()
                , 898);
    }

    /**
     * Tests the total number of socials with efteling username
     */
    @Test
    public void getByUsername() {
        Assert.assertEquals(sparkReader.getByUsername("efteling").size()
                , 40);
    }

    /**
     * Tests the total number of socials of VIDEO type
     */
    @Test
    public void getBySocialType() {
        Assert.assertEquals(sparkReader.getBySocialType(SocialTypeEnum.VIDEO).size()
                , 100);
    }

    /**
     * Tests the total number of socials containing the word "disney"
     */
    @Test
    public void getByContains() {
        Assert.assertEquals(sparkReader.getByContains("disney").size()
                , 17);
    }


    /**
     * Tests the total number of socials not containing the word "disney"
     */
    @Test
    public void getByNotContains() {
        Assert.assertEquals(sparkReader.getByNotContains("disney").size()
                , 881);
    }

    /**
     * The total sum of a contains plus not contains should be equal to the total of records in the rdd
     */
    @Test
    public void containsPlusNotContains() {
        Assert.assertEquals(sparkReader.getByContains("disney").size()
                        + sparkReader.getByNotContains("disney").size()
                , sparkReader.getAll().size());
    }

    /**
     * Tests the total number of socials created before a given date
     */
    @Test
    public void getBeforeDate() {
        Assert.assertEquals(sparkReader.getBeforeDate(LocalDate.of(2017, Month.FEBRUARY, 1)).size()
                , 227);
    }

    /**
     * Tests the total number of socials created in a given date
     */
    @Test
    public void getEqualsDate() {
        Assert.assertEquals(sparkReader.getEqualsDate(LocalDate.of(2017, Month.FEBRUARY, 1)).size()
                , 12);
    }

    /**
     * Tests the total number of socials created after a given date
     */
    @Test
    public void getAfterDate() {
        Assert.assertEquals(sparkReader.getAfterDate(LocalDate.of(2017, Month.FEBRUARY, 1)).size()
                , 659);
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
