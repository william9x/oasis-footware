package com.oasisvn.service.user;

import com.oasisvn.model.dto.user.UserDTO;
import com.oasisvn.model.dto.user.UserSession;
import com.oasisvn.model.io.request.user.UserLoginRequest;

public interface IUserService {
    UserSession login(UserLoginRequest req);
    UserDTO createUser(UserDTO userDTO);
}
