package hackovid2020.back.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private String mail;

    private String password;

    @JsonCreator
    private LoginRequest(
            @JsonProperty("mail") String mail,
            @JsonProperty("password") String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

}
