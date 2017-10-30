package com.example.codeChallenge;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service @Lazy
public class SparkJsonFileReader implements SparkReader {

    private Dataset<Row> socials;

    public SparkJsonFileReader(@Value("${json.path}") String jsonPath,
                               @Value("${spring.application.name}") String applicationName) {
        SparkSession sparkSession = SparkSession.builder()
                .master("local")
                .appName(applicationName)
                .getOrCreate();

//		TODO make use of resources claspath
        String workingDir = System.getProperty("user.dir");
        socials = sparkSession
                .read()
                .option("multiLine", true)
                .option("mode", "PERMISSIVE")
                .json(workingDir + jsonPath).cache();
	}

    @Override
    public List<String> getAll() {
        return socials.select(org.apache.spark.sql.functions.explode(socials.col("socials")).as("socials")).toJSON().collectAsList();
    }

    @Override
    public List<String> getByUsername(String username) {
        return socials.select(org.apache.spark.sql.functions.explode(socials.col("socials")).as("socials")).where("socials.username = '" + username + "'").toJSON().collectAsList();
    }

    @Override
    public List<String> getBySocialType(SocialTypeEnum contentType) {
        return socials.select(org.apache.spark.sql.functions.explode(socials.col("socials")).as("socials")).where("socials.socialType = '" + contentType.getValue() + "'").toJSON().collectAsList();
    }

    @Override
    public List<String> getByContains(String word) {
        return socials.select(org.apache.spark.sql.functions.explode(socials.col("socials")).as("socials")).where("socials.content like '%" + word + "%'").toJSON().collectAsList();
    }

    @Override
    public List<String> getByNotContains(String word) {
        return socials.select(org.apache.spark.sql.functions.explode(socials.col("socials")).as("socials")).where("socials.content is null or socials.content not like '%" + word + "%'").toJSON().collectAsList();
    }

    @Override
    public List<String> getBeforeDate(LocalDate localDate) {
        return socials.select(org.apache.spark.sql.functions.explode(socials.col("socials")).as("socials")).where("socials.timestamp is not null and to_date(socials.timestamp) < '" + localDate + "'").toJSON().collectAsList();
    }

    @Override
    public List<String> getEqualsDate(LocalDate localDate) {
        return socials.select(org.apache.spark.sql.functions.explode(socials.col("socials")).as("socials")).where("socials.timestamp is not null and to_date(socials.timestamp) = '" + localDate + "'").toJSON().collectAsList();
    }

    @Override
    public List<String> getAfterDate(LocalDate localDate) {
        return socials.select(org.apache.spark.sql.functions.explode(socials.col("socials")).as("socials")).where("socials.timestamp is not null and to_date(socials.timestamp) > '" + localDate + "'").toJSON().collectAsList();
    }
}