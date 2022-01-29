package com.example.order.service.dto;

import com.example.order.enums.SearchType;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDTO {

  private Integer page;

  private Integer size;

  private String searchValue;

  private SearchType searchType;

  public Integer getPage() {
    if (null == this.page) return 0;
    return page;
  }

  public Integer getSize() {
    if (null == this.size) return 10;
    return size;
  }

  public SearchType getSearchType() {
    if (null == this.searchType) return SearchType.NONE;
    return this.searchType;
  }
}
