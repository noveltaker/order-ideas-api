package com.example.order.web.docs;

import com.example.order.service.dto.LoginDTO;
import io.swagger.v3.oas.annotations.Operation;

public interface ViewResourceDocs {

  @Operation(summary = "로그인 API")
  void login(LoginDTO dto);

  @Operation(summary = "로그아웃 API")
  void logout(Long userId);
}
