package com.oasisvn.repository.user;

import com.oasisvn.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity save(UserEntity userEntity);
    UserEntity findByUsername(String username);
    UserEntity findByUserId(String userId);
}
