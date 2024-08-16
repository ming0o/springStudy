package com.study.spring.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * "code" : "400",
 * "message":"잘못된 요청입니다."
 * "validation" : {
 *     "title" : "제목을 입력하세요"
 * }
 */

@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) { // 생성자 단축키 cmd+n
        this.code = code;
        this.message = message;
        this.validation = validation != null ? validation : new HashMap<>();
    }

    public void addValidation(String field, String message) {
        this.validation.put(field, message);
    }
}
