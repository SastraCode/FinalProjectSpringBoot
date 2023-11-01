package com.banksultra.finalProject.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomeResponse<T> {
    private int code;
    private String message;
    private T data;
}
