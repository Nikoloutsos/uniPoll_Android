package com.androiddreamer.unipoll.util;

public class RegexUtil {
    public static final String mailRegex = "[^@]+@[^\\.]+\\..+";

    public static boolean isEmail(String mail) {
        return mail.matches(mailRegex);
    }
}

