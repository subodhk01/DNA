package com.dnamedical.Models.registration;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistartionData {

@SerializedName("name")
@Expose
private String name;
@SerializedName("username")
@Expose
private String username;
@SerializedName("email_id")
@Expose
private String emailId;
@SerializedName("password")
@Expose
private String password;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getEmailId() {
return emailId;
}

public void setEmailId(String emailId) {
this.emailId = emailId;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

}