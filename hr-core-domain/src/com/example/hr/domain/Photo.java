package com.example.hr.domain;

import java.util.Base64;

import com.example.ddd.ValueObject;

@ValueObject
public record Photo(byte[] values) {
   
   public String toBase64() {
	   return Base64.getEncoder().encodeToString(values);
   }
   
   public static Photo of(String base64Values) {
	   return new Photo(Base64.getDecoder().decode(base64Values));
   }
	
}
