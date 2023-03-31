package entity;

import lombok.Data;

@Data
public class User {
    private String email;
    private Integer id;
    private String username;
    private String first_name;
    private String last_name;
    private Boolean is_active;
    private String date_joined;
    private String role;
    private String date_birth;

}
