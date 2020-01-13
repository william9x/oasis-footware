package com.oasisvn.middleware.utilities.crypto;

import com.oasisvn.middleware.utilities.ICustomUtilities;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class CryptoUtils implements ICustomUtilities {

    @Override
    public String encodeBase64UrlSafe(String string) {

        String encodedString = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(string.getBytes());

        return encodedString;
    }

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    @Override
    public boolean checkPassword(String pwd1, String pwd2) {
        return BCrypt.checkpw(pwd1, pwd2);
    }
}
