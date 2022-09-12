package com.genspark.babygotbackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChangePasswordResponse {

    private String email;
    private String status;
}
