package com.hele.model.Converters;


import com.hele.dto.LoginDto;
import com.hele.model.frontObjects.LoginData;
import org.springframework.stereotype.Component;

@Component
public class FrontLoginConverter {

    public LoginDto toLoginDto(final LoginData loginData) {
        return LoginDto.builder()
                .username(loginData.getUsername())
                .password(loginData.getPassword())
                .build();
    }

    public LoginData toFrontEntity(final LoginDto loginDto) {
        return LoginData.builder()
                .username(loginDto.getUsername())
                .password(loginDto.getPassword())
                .build();
    }
}
