package com.example.demo.features.user.enums;

public enum UsagePurpose {
  CREATE, GROW;

  public static UsagePurpose fromString(String purpose) {
    try {
      return UsagePurpose.valueOf(purpose.toUpperCase());
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid purpose");
    }
  }
}
