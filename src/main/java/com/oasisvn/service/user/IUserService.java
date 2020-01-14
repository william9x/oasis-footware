package com.oasisvn.service.user;

import com.oasisvn.model.dto.user.UserDTO;
import com.oasisvn.model.dto.user.UserSession;
import com.oasisvn.model.io.request.user.UserLoginRequest;
import com.oasisvn.model.io.response.token.TokenResponse;

public interface IUserService {
    TokenResponse login(UserLoginRequest req);
    UserDTO createUser(UserDTO userDTO);
}
