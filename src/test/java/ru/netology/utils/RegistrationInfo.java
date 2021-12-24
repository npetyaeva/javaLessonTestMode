package ru.netology.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationInfo {
    private final String login;
    private final String password;
    private final String status;
}
