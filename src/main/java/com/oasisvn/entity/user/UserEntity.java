package com.oasisvn.entity.user;

import com.oasisvn.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4273831224513441479L;

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, name = "user_id")
    private String userId;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(length = 15)
    private String phone;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column(name="full_name", length = 50)
    private String fullName;

    @Column(nullable = false)
    private String role;
}
