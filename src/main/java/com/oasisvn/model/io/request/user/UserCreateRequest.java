package com.oasisvn.model.io.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "email required")
    @NotNull(message = "email required")
    @Size(max = 50, message = "email can only have maximum 50 characters")
    @ApiModelProperty(
            example = "test@test.com",
            notes = "email cannot be empty",
            required = true
    )
    private String email;

    @NotBlank(message = "username required")
    @NotNull(message = "username required")
    @Size(max = 50, message = "username can only have maximum 100 characters")
    @ApiModelProperty(
            example = "leather",
            notes = "username cannot be empty",
            required = true
    )
    private String username;

    @Size(max = 15, message = "username can only have maximum 15 characters")
    @ApiModelProperty(
            example = "09090909090",
            notes = "phone can be empty"
    )
    private String phone;

    @NotBlank(message = "password required")
    @NotNull(message = "password required")
    @ApiModelProperty(
            example = "leather",
            notes = "password cannot be empty",
            required = true
    )
    private String password;

    @Size(max = 50, message = "fullName can only have maximum 50 characters")
    @ApiModelProperty(
            example = "leather",
            notes = "username cannot be empty"
    )
    private String fullName;
}
