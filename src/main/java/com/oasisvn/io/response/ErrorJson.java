package com.oasisvn.io.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@Component
public class ErrorJson {

    private Integer status;
    private boolean success;
    private String message;
    private Object data;

    public ErrorJson(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.success = false;
        this.message = (String) errorAttributes.get("error");
        this.data = null;
    }
}
