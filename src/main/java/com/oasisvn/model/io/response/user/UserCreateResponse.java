package com.oasisvn.model.io.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {
    private String userId;
    private String email;
    private String username;
    private String phone;
    private String fullName;
}
