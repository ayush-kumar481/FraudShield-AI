package com.frauddetection.backend.service;

import com.frauddetection.backend.dto.request.LoginRequest;
import com.frauddetection.backend.dto.request.RegisterUserRequest;
import com.frauddetection.backend.dto.response.LoginResponse;
import com.frauddetection.backend.dto.response.UserResponse;
import com.frauddetection.backend.entity.User;
import com.frauddetection.backend.enums.AccountStatus;
import com.frauddetection.backend.enums.UserRole;
import com.frauddetection.backend.exception.DuplicateResourceException;
import com.frauddetection.backend.exception.InvalidCredentialsException;
import com.frauddetection.backend.exception.ResourceNotFoundException;
import com.frauddetection.backend.repository.UserRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse registerUser(RegisterUserRequest request) {
        log.info("Registration requested for email={}", request.email());

        if (userRepository.existsByEmail(request.email())) {
            log.warn("Registration rejected: email already registered");
            throw DuplicateResourceException.of("User", "email", request.email());
        }

        User user = User.builder()
                .fullName(request.fullName())
               
                .passwordHash(placeholderHash(request.password()))
                .email(request.email())
     
                .role(UserRole.USER)
               
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);
        log.info("User registered: userId={}", savedUser.getUserId());

        return toUserResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(String userId) {
        return userRepository.findById(userId)
                .map(this::toUserResponse)
                .orElseThrow(() -> ResourceNotFoundException.of("User", userId));
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::toUserResponse)
                .orElseThrow(() -> ResourceNotFoundException.of("User", email));
    }


    private String placeholderHash(String plaintextPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(plaintextPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashed);
        } catch (NoSuchAlgorithmException e) {
            
            throw new IllegalStateException("SHA-256 algorithm unavailable in this JVM.", e);
        }
    }

    private UserResponse toUserResponse(User user) {
    	return UserResponse.builder()
    	        .userId(user.getUserId())
    	        .fullName(user.getFullName())
    	        .email(user.getEmail())
    	        .role(user.getRole())
    	        .accountStatus(user.getAccountStatus())
    	        .walletBalance(user.getWalletBalance())
    	        .createdAt(user.getCreatedAt())
    	        .build();
    }
    
    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        InvalidCredentialsException.of());

        String incomingHash = placeholderHash(request.password());

        if (!incomingHash.equals(user.getPasswordHash())) {
            throw InvalidCredentialsException.of();
        }

        return LoginResponse.builder()
                .accessToken("dummy-access-token")
                .refreshToken("dummy-refresh-token")
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .accountStatus(user.getAccountStatus())
                .build();
    }
    
    @Override
    public void changePassword(
            String userId,
            String currentPassword,
            String newPassword
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        ResourceNotFoundException.of("User", userId));

        String currentHash = placeholderHash(currentPassword);

        if (!currentHash.equals(user.getPasswordHash())) {
            throw InvalidCredentialsException.of();
        }

        user.setPasswordHash(placeholderHash(newPassword));

        userRepository.save(user);

        log.info("Password changed successfully for user={}", userId);
    }
}

