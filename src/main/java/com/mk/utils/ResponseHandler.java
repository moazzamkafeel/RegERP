package com.mk.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status,String error, Object responseObj) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("message", message);
            map.put("status", status.value());
            map.put("data", responseObj);
            map.put("error",error);
            return new ResponseEntity<Object>(map,status);
    } 
}
