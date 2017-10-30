package com.example.codeChallenge;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.example.codeChallenge.SocialTypeEnum.FACEBOOK;
import static com.example.codeChallenge.SocialTypeEnum.VIDEO;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @Autowired
	public SparkReader sparkReader;

    @RequestMapping("/")
    @ResponseBody
    List<String> index() {
        return sparkReader.getAll();
    }

    @RequestMapping("/0")
    @ResponseBody
    List<String> actor0() {
        return sparkReader.getByUsername("efteling");
    }

    @RequestMapping("/1")
    @ResponseBody
    List<String> actor1() {
        return sparkReader.getByContains("disney");
    }

    @RequestMapping("/2")
    @ResponseBody
    List<String> actor2() {
        return sparkReader.getByNotContains("disney");
    }

    @RequestMapping("/3")
    @ResponseBody
    List<String> actor3() {
        return sparkReader.getBySocialType(VIDEO);
    }

    @RequestMapping("/4")
    @ResponseBody
    List<String> actor4() {
        return sparkReader.getBeforeDate(LocalDate.of(2017, Month.FEBRUARY, 1));
    }

    @RequestMapping("/5")
    @ResponseBody
    List<String> actor5() {
        return sparkReader.getAfterDate(LocalDate.of(2017, Month.FEBRUARY, 1));
    }

    @RequestMapping("/6")
    @ResponseBody
    List<String> actor6() {
        return sparkReader.getBySocialType(FACEBOOK);
    }
}