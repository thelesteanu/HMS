package com.hele.model.frontObjects;

import lombok.*;

/**
 * Created by thelesteanu on 25.04.2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginData {
    String username;
    String password;
}
