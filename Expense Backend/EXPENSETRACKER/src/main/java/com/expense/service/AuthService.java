package com.expense.service;

import com.expense.dto.request.LoginRequest;
import com.expense.dto.request.RegisterRequest;
import com.expense.dto.response.LoginResponse;
import com.expense.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}