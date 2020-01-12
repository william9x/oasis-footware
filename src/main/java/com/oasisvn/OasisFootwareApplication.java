package com.oasisvn;

import com.oasisvn.entity.user.UserEntity;
import com.oasisvn.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class OasisFootwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasisFootwareApplication.class, args);
    }
}
