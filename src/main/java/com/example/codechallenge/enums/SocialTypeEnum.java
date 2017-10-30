package com.example.codechallenge.enums;

/**
 * A enumerator of the available social types
 */
public enum SocialTypeEnum {

    VIDEO("INSTAGRAM_VIDEO"),
    FACEBOOK("FACEBOOK_POST");

    private String value;

    SocialTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}