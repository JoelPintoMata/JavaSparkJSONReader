package com.example.codeChallenge.readers;


import com.example.codeChallenge.enums.SocialTypeEnum;

import java.time.LocalDate;
import java.util.List;

/**
 * A Spark reader interface
 */
public interface SparkReader {

    /**
     * Returns all existing socials
     * @return all existing socials
     */
    List<String> getAll();

    /**
     * Returns all socials having a given username
     * @param username the username
     * @return all socials having a given username
     */
    List<String> getByUsername(String username);

    /**
     * Returns all socials having a given content type [[com.example.codeChallenge.enums.SocialTypeEnum]]
     * @param contentType the content type
     * @return all socials having a given content type
     */
    List<String> getBySocialType(SocialTypeEnum contentType);

    /**
     * Returns all socials having a given work
     * @param word the word
     * @return all socials having a given work
     */
    List<String> getByContains(String word);

    /**
     * Returns all socials not having a given work
     * @param word the word
     * @return all socials not having a given work
     */
    List<String> getByNotContains(String word);

    /**
     * Returns all socials created before a given date
     * @param localDate the date
     * @return all socials created before a given date
     */
    List<String> getBeforeDate(LocalDate localDate);

    /**
     * Returns all socials created on a given date
     * @param localDate the date
     * @return all socials created on a given date
     */
    List<String> getEqualsDate(LocalDate localDate);

    /**
     * Returns all socials created after a given date
     * @param localDate the date
     * @return all socials created after a given date
     */
    List<String> getAfterDate(LocalDate localDate);
}

