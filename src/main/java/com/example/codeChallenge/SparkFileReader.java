package com.example.codeChallenge;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.time.LocalDate;
import java.util.List;

public class SparkFileReader implements SparkReader {

    public JavaRDD<String> rdd;

	public SparkFileReader() {
        SparkConf conf = new SparkConf()
                .setMaster("local[1]")
                .setAppName("codeChallenge");
		JavaSparkContext sc = new JavaSparkContext(conf);

//		TODO make use of resources claspath
        String workingDir = System.getProperty("user.dir");
        rdd = sc.textFile(workingDir + "/src/main/resources/socials.json").cache();
	}

    @Override
    public List<String> getAll() {
        return null;
    }

    @Override
    public List<String> getByUsername(String efteling) {
        return null;
    }

    @Override
    public List<String> getBySocialType(SocialTypeEnum contentType) {
        return null;
    }

    @Override
    public List<String> getByContains(String word) {
        return null;
    }

    @Override
    public List<String> getByNotContains(String word) {
        return null;
    }

    @Override
    public List<String> getBeforeDate(LocalDate localDate) {
        return null;
    }

    @Override
    public List<String> getEqualsDate(LocalDate localDate) {
        return null;
    }

    @Override
    public List<String> getAfterDate(LocalDate localDate) {
        return null;
    }
}
