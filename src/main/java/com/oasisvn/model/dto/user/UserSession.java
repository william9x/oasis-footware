package com.oasisvn.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements Serializable {
    private String userId;
    private String role;

    @Override
    public String toString() {
        return "UserSession{" +
                "userId=" + userId +
                ", role='" + role + '\'' +
                '}';
    }
}
