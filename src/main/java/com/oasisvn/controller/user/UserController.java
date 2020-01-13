package com.oasisvn.controller.user;

import com.oasisvn.model.dto.user.UserDTO;
import com.oasisvn.model.dto.user.UserSession;
import com.oasisvn.model.io.request.user.UserCreateRequest;
import com.oasisvn.model.io.request.user.UserLoginRequest;
import com.oasisvn.model.io.response.ErrorResponse;
import com.oasisvn.model.io.response.OperationStatus;
import com.oasisvn.model.io.response.SuccessResponse;
import com.oasisvn.model.io.response.user.UserCreateResponse;
import com.oasisvn.service.user.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
@Api(value = "User API")
public class UserController {

    private ModelMapper modelMapper = new ModelMapper();
    private OperationStatus operationStatus = new OperationStatus();

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Login", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest loginRequest, HttpServletRequest request) {

        UserSession result = userService.login(loginRequest);

        if (null == result) {
            operationStatus = new OperationStatus(HttpStatus.NOT_FOUND.value(),false,
                    ErrorResponse.AUTHENTICATION_FAILED.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);
        } else {

            request.getSession().setAttribute("OASIS_SESSION", result);

            operationStatus = new OperationStatus(HttpStatus.OK.value(),true,
                    SuccessResponse.AUTHENTICATED.getSuccessResponse(), null);

            return ResponseEntity.status(HttpStatus.OK).body(operationStatus);
        }
    }

    @ApiOperation(value = "Create a user", response = OperationStatus.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserCreateRequest request) {
        OperationStatus operationStatus;

        UserDTO userDTO = modelMapper.map(request, UserDTO.class);
        UserDTO createdUser = userService.createUser(userDTO);

        if (null == createdUser) {
            operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
                    ErrorResponse.COULD_NOT_CREATE_RECORD.getErrorMessage(), null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);
        } else {
            UserCreateResponse returnValue = modelMapper.map(createdUser, UserCreateResponse.class);

            operationStatus = new OperationStatus(HttpStatus.CREATED.value(), true,
                    SuccessResponse.CREATED_RECORD.getSuccessResponse(), returnValue);

            return ResponseEntity.status(HttpStatus.CREATED).body(operationStatus);
        }
    }
}
