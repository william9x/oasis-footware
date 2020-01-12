package com.oasisvn.service.user;

import com.oasisvn.model.dto.user.UserDTO;
import com.oasisvn.entity.user.UserEntity;
import com.oasisvn.repository.user.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        try {
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
            userEntity.setRole("ADMIN");

            String userId = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(userEntity.getUsername().getBytes());
            userEntity.setUserId(userId);

            String hash = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12));
            userEntity.setEncryptedPassword(hash);

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