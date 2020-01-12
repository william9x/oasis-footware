package com.oasisvn.service.user;

import com.oasisvn.middleware.utilities.ICustomUtilities;
import com.oasisvn.model.dto.user.UserDTO;
import com.oasisvn.entity.user.UserEntity;
import com.oasisvn.model.dto.user.UserSession;
import com.oasisvn.model.io.request.user.UserLoginRequest;
import com.oasisvn.repository.user.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserSession login(UserLoginRequest loginRequest) {

        try {
            UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername());
            if (null == userEntity) return null;
            else {
                boolean result = utilities.checkPassword(
                        loginRequest.getPassword(),
                        userEntity.getEncryptedPassword());

                if (false == result) return null;
                else {
                    return modelMapper.map(userEntity, UserSession.class);
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        try {
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

            userEntity.setUserId(utilities.encodeBase64UrlSafe(userEntity.getUsername()));
            userEntity.setEncryptedPassword(utilities.encodePassword(userDTO.getPassword()));
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