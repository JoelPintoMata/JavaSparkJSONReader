package com.example.codeChallenge;

import java.time.LocalDate;
import java.util.List;

public interface SparkReader {

    List<String> getAll();

    List<String> getByUsername(String username);

    List<String> getBySocialType(SocialTypeEnum contentType);

    List<String> getByContains(String word);

    List<String> getByNotContains(String word);

    List<String> getBeforeDate(LocalDate localDate);

    List<String> getEqualsDate(LocalDate localDate);

    List<String> getAfterDate(LocalDate localDate);
}

