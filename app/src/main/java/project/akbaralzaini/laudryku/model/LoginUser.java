package project.akbaralzaini.laudryku.model;

import com.google.gson.annotations.SerializedName;

public class LoginUser {
    @SerializedName("email_user")
    private String email_user;
    @SerializedName("password_user")
    private String password_user;

    public LoginUser(String email_user, String password_user) {
        this.email_user = email_user;
        this.password_user = password_user;
    }

    public LoginUser() {
        this.email_user = "";
        this.password_user = "";
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }
}
