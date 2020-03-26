package com.nixsolutions.form;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Data
@XmlRootElement
public class LoginForm {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginForm(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginForm() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginForm loginForm = (LoginForm) o;
        return login.equals(loginForm.login) &&
                password.equals(loginForm.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
