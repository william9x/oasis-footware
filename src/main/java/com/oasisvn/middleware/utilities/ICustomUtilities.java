package com.oasisvn.middleware.utilities;

public interface ICustomUtilities {
    String encodeBase64UrlSafe(String string);
    String hashPassword(String password);
    String generateUUID();
    boolean checkPassword(String pwd1, String pwd2);
}
