package entity.request;

import lombok.Data;
@Data
public class UserRequestForCreate {

    private String email;
    private String username;
    private String password;

}
