package project.akbaralzaini.laudryku.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("email_user")
    private String email_user;
    @SerializedName("password_user")
    private String password_user;
    @SerializedName("tipe")
    private String tipe;
    @SerializedName("create_at")
    private String create_at;

    public User(){
        this.id_user = "";
        this.email_user = "";
        this.password_user = "";
        this.tipe = "";
        this.create_at = "";
    }
    public User(String email_user, String password_user, String tipe) {
        this.email_user = email_user;
        this.password_user = password_user;
        this.tipe = tipe;
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

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

}
