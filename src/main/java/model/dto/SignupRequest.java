package model.dto;

import java.util.Set;

public record SignupRequest(String username, String password, String email, Set<String> roles) {
}
