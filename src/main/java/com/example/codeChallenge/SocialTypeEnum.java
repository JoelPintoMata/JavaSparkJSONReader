package com.example.codeChallenge;

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