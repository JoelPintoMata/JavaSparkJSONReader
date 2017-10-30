package com.example.codechallenge.readers;


import com.example.codechallenge.enums.SocialTypeEnum;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * A Spark reader implementation based on JSON files and SQL querying
 *
 * Note: @Lazy avoids errors related with more than one spark context in the same application.
 * These can happen when implementing more than one SparkReader
 */
@Service @Lazy
public class SparkJsonFileReader implements SparkReader {

    private Dataset<Row> socials;

//    convinience dataset with formatted/exploded external `socials` node
    private Dataset<Row> socialsSelectDataSetRow;

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
                .json(workingDir + jsonPath)
                .cache();

        socialsSelectDataSetRow = socials.select(functions.explode(socials.col("socials")).as("socials"));
	}

    @Override
    public List<String> getAll() {
        return socialsSelectDataSetRow.toJSON().collectAsList();
    }

    @Override
    public List<String> getByUsername(String username) {
        return socialsSelectDataSetRow.where("socials.username = '" + username + "'").toJSON().collectAsList();
    }

    @Override
    public List<String> getBySocialType(SocialTypeEnum contentType) {
        return socialsSelectDataSetRow.where("socials.socialType = '" + contentType.getValue() + "'").toJSON().collectAsList();
    }

    @Override
    public List<String> getByContains(String word) {
        return socialsSelectDataSetRow.where("socials.content like '%" + word + "%'").toJSON().collectAsList();
    }

    @Override
    public List<String> getByNotContains(String word) {
        return socialsSelectDataSetRow.where("socials.content is null or socials.content not like '%" + word + "%'").toJSON().collectAsList();
    }

    @Override
    public List<String> getBeforeDate(LocalDate localDate) {
        return socialsSelectDataSetRow.where("socials.timestamp is not null and to_date(socials.timestamp) < '" + localDate + "'").toJSON().collectAsList();
    }

    @Override
    public List<String> getEqualsDate(LocalDate localDate) {
        return socialsSelectDataSetRow.where("socials.timestamp is not null and to_date(socials.timestamp) = '" + localDate + "'").toJSON().collectAsList();
    }

    @Override
    public List<String> getAfterDate(LocalDate localDate) {
        return socialsSelectDataSetRow.where("socials.timestamp is not null and to_date(socials.timestamp) > '" + localDate + "'").toJSON().collectAsList();
    }
}