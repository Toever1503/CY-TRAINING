package com.entity.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Data
public class ResponseData {
    private Object data;
    private String message;
    private String result;

    static HttpStatus getCodeStatus(Object data) {
        return data == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
    }

    public static ResponseEntity<?> create(Object data) {
        return new ResponseEntity<>(ResponseData.builder()
                .data(data)
                .result(data == null ? "error" : "success")
                .message(data == null ? "Created failed!" : "Created successfully!")
                .build(),
                data == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
    }

    public static ResponseEntity<?> update(Object data) {
        return new ResponseEntity<>(ResponseData.builder()
                .data(data)
                .result(data == null ? "error" : "success")
                .message(data == null ? "Updated failed!" : "Updated successfully!")
                .build(),
                getCodeStatus(data));
    }

    public static ResponseEntity<?> delete(Object data) {
        return new ResponseEntity<>(ResponseData.builder()
                .data(data)
                .result((boolean) data == false ? "error" : "success")
                .message((boolean) data == false ? "Deleted failed!" : "Deleted successfully!")
                .build(),
                (boolean) data == false ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    public static ResponseEntity<?> get(Object data) {
        return new ResponseEntity<>(ResponseData.builder()
                .data(data)
                .result(data == null ? "error" : "success")
                .message(data == null ? "Get data failed!" : "Get data successfully!")
                .build(),
                getCodeStatus(data));
    }

}
