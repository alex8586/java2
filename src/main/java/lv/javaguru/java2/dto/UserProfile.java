package lv.javaguru.java2.dto;


import lombok.Getter;
import lombok.Setter;

public class UserProfile {
    @Getter
    @Setter
    private String fullName;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String repeatPassword;
}
