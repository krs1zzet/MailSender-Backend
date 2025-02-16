package com.example.demo.features.user.service;


import com.example.demo.features.user.dto.FullNameRequestDTO;
import com.example.demo.features.user.dto.UsagePurposeRequestDTO;
import com.example.demo.features.user.entity.UserEntity;

public interface UserService {

  UserEntity findByEmail(String email);

  UserEntity findById(Integer id);

  void saveUser(UserEntity user);

  void updateFullName(FullNameRequestDTO fullName);

  boolean existsByEmail(String email);

  void updateUserPurpose(UsagePurposeRequestDTO userPurposeRequest);
}
