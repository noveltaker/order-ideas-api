package com.example.order.service.dto;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDTO {

  private Integer page;

  private Integer size;

  private String searchString;

  public PageDTO(Integer page, Integer size, String searchString) {
    this.page = page;
    this.size = size;
    this.searchString = searchString;
  }

  public Integer getPage() {
    if (null == this.page) return 0;
    return page;
  }

  public Integer getSize() {
    if (null == this.size) return 10;
    return size;
  }

  public boolean isNotEmptySearch() {
    return null != this.searchString;
  }
}
