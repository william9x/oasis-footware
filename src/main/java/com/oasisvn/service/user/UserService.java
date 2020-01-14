package com.oasisvn.service.user;

import com.oasisvn.entity.user.UserEntity;
import com.oasisvn.middleware.security.SecurityConstants;
import com.oasisvn.middleware.utilities.ICustomUtilities;
import com.oasisvn.middleware.utilities.jwt.JwtUltils;
import com.oasisvn.model.dto.user.UserDTO;
import com.oasisvn.model.dto.user.UserSession;
import com.oasisvn.model.io.request.user.UserLoginRequest;
import com.oasisvn.model.io.response.token.TokenResponse;
import com.oasisvn.repository.user.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICustomUtilities utilities;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public TokenResponse login(UserLoginRequest loginRequest) {

        try {
            // Lấy thông tin user
            UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername());

            if (null == userEntity) return null;

            boolean isPasswordMatched = utilities.checkPassword(
                    loginRequest.getPassword(),
                    userEntity.getEncryptedPassword());

            if (false == isPasswordMatched) return null;

            String token = JwtUltils.generateToken(userEntity);
            return new TokenResponse(token, SecurityConstants.EXPIRATION);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        try {
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

            userEntity.setUserId(utilities.encodeBase64UrlSafe(userEntity.getUsername()));
            userEntity.setEncryptedPassword(utilities.hashPassword(userDTO.getPassword()));
            userEntity.setRole("ADMIN");

            UserEntity createdUser = userRepository.save(userEntity);

            return modelMapper.map(createdUser, UserDTO.class);

        } catch (Exception e) {
            return null;
        }
    }

//    private void createSuperAdmin() {
//        UserEntity superAdmin = new UserEntity();
//        superAdmin.setRole("SUDO");
//        superAdmin.setUserId("superadmin");
//        superAdmin.setUsername("superadmin");
//        superAdmin.setEmail("lehoangnam197@gmail.com");
//        String hash = BCrypt.hashpw("Hnam@11", BCrypt.gensalt(12));
//        superAdmin.setPassword(hash);
//        superAdmin.setPhone("0909090909");
//        superAdmin.setFullName("Nam");
//        userRepository.save(superAdmin);
//    }
}