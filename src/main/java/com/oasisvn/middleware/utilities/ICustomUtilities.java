package com.oasisvn.middleware.utilities;

public interface ICustomUtilities {
    String encodeBase64UrlSafe(String string);
    String encodePassword(String password);
    boolean checkPassword(String pwd1, String pwd2);
}
