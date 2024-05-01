package com.berkhayta.springbootblogrestapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType
{
  POST_NOT_FOUND(5001,"Böyle bir Post bulunamadı.", HttpStatus.NOT_FOUND),
  USER_NOT_FOUND(5002,"Böyle bir Kullanici bulunamadı.", HttpStatus.NOT_FOUND),
  CATEGORY_NOT_FOUND(5003,"Kategori bulunamadı.", HttpStatus.NOT_FOUND);

  private Integer code;
  private String message;
  private HttpStatus status;
}
