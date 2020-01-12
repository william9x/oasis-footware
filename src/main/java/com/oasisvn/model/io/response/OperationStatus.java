package com.oasisvn.model.io.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationStatus {
    private int status;
    private boolean success;
    private String message;
    private Object data;

    public OperationStatus(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.success = false;
        this.message = (String) errorAttributes.get("error");
        this.data = null;
    }
}
