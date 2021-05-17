package com.hele.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private String username;
    private String password;
}
