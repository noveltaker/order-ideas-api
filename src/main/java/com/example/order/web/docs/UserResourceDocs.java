package com.example.order.web.docs;

import com.example.order.domain.Order;
import com.example.order.domain.User;
import com.example.order.service.dto.IPageUser;
import com.example.order.service.dto.IUser;
import com.example.order.service.dto.PageDTO;
import com.example.order.service.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserResourceDocs {

  @Operation(summary = "회원가입 API")
  User joinUser(UserDTO userDTO);

  @Operation(summary = "단일 회원 상세 정보 조회 기능 API")
  IUser showUser(Long id);

  @Operation(summary = "단일 회원의 주문 목록 조회 API (no paging)")
  List<Order> showOrderListByUser(Long userId);

  @Operation(
      summary = "여러 회원 조회 목록 조회 API",
      description =
          "1. 페이지 네이션으로 일정 단위를 조회한다." + "2. 이름, 이메일을 이용하여서 검색 기능이 필요" + "3. 각 회원의 마지막 주문 정보")
  Page<IPageUser> showUsers(PageDTO dto);
}
