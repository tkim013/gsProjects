package com.genspark.babygotbackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteUserResponse {

    private String user;
    private String status;
}
