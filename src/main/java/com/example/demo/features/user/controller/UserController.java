package com.example.demo.features.user.controller;


import com.example.demo.features.user.dto.FullNameRequestDTO;
import com.example.demo.features.user.dto.UsagePurposeRequestDTO;
import com.example.demo.features.user.entity.UserEntity;
import com.example.demo.features.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/profile/{id}")
  public ResponseEntity<UserEntity> getUserProfile(@PathVariable Integer id) {
    UserEntity user = userService.findById(id);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping("/full-name")
  public ResponseEntity<Void> updateUserName(
      @RequestBody
      @Valid
      FullNameRequestDTO updateFullName
  ) {
    userService.updateFullName(updateFullName);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/usage-purpose")
  public ResponseEntity<Void> updateUserPurpose(
      @RequestBody
      @Valid
      UsagePurposeRequestDTO usagePurposeRequest
  ) {
    userService.updateUserPurpose(usagePurposeRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
