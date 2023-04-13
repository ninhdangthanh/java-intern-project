package com.study.backend.request;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordReset {
    private String old_password;
    private String new_password;
    private String confirm_password;
}
