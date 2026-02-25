package com.foodie.user_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JwtResponse {
   private String token;

   public JwtResponse(String token) {
      this.token = token;
   }
}
