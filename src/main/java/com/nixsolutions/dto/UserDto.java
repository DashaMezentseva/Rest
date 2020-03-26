package com.nixsolutions.dto;

import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import java.sql.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement
public class UserDto {

    protected Long userId;
    @Size(min = 4, max = 20)
    protected String login;
    protected String password;
    private String passwordAgain;
    @Email
    @Size(min = 4, max = 20)
    protected String email;
    @NotEmpty
    protected String firstName;
    @NotEmpty
    protected String lastName;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date birthday;

    protected Role role;

    public static UserDto userToDto(User user) {
        UserDto userDto = new UserDto(user.getUserId(), user.getLogin(), user.getPassword(),
                user.getPassword(), user.getEmail(), user.getFirstName(),
                user.getLastName(), new java.sql.Date(user.getBirthday().getTime()), user.getRole());
        return userDto;
    }

    public static User dtoToUser(UserDto userDto) {
        User user = new User(userDto.getUserId(), userDto.getLogin(), userDto. getPassword(),
                userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(),
                userDto.getBirthday(), userDto.getRole());
        return user;
    }


    //    public static UserDto userToDto(User user) {
//        UserDto userDto = new UserDto(user.getUserId(), user.getLogin(),
//            user.getPassword(), user.getPassword(), user.getEmail(), user.getFirstName(),
//            user.getLastName(), user.getBirthday(), user.getRole());
//        return userDto;
//    }
//
//    public static User dtoToUser(UserDto userDto) {
//        User user = new User(userDto.getUserId(), userDto.getLogin(), userDto.getPassword()
//            , userDto.getEmail(), userDto.getFirstName(), userDto.getLastName()
//            , new java.sql.Date(userDto.getBirthday().getTime()), userDto.getRole());
//        return user;
//    }

    public UserDto() {
    }

    public UserDto(Long userId, @Size(min = 4, max = 20) String login,
                   String password, String passwordAgain, @Size(min = 4, max = 20) String email,
                   String firstName, String lastName, @NotNull @Past Date birthday, Role role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return userId.equals(userDto.userId) &&
                login.equals(userDto.login) &&
                password.equals(userDto.password) &&
                Objects.equals(passwordAgain, userDto.passwordAgain) &&
                email.equals(userDto.email) &&
                firstName.equals(userDto.firstName) &&
                lastName.equals(userDto.lastName) &&
                birthday.equals(userDto.birthday) &&
                role.equals(userDto.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, passwordAgain, email, firstName, lastName, birthday, role);
    }

    //    public static UserDto userToDto(User user) {
//        UserDto userDto = new UserDto(user.getUserId(), user.getLogin(),
//            user.getPassword(), user.getPassword(), user.getEmail(), user.getFirstName(),
//            user.getLastName(), user.getBirthday(), user.getRole());
//        return userDto;
//    }
//
//    public static User dtoToUser(UserDto userDto) {
//        User user = new User(userDto.getUserId(), userDto.getLogin(), userDto.getPassword()
//            , userDto.getEmail(), userDto.getFirstName(), userDto.getLastName()
//            , new java.sql.Date(userDto.getBirthday().getTime()), userDto.getRole());
//        return user;
//    }

//    public UserDto() {
//    }
//
//    public UserDto(final Long userId, final String login, final String password, final String passwordAgain,
//                   final String email, final String firstName, final String lastName,
//                   final Date birthday, final Role role) {
//        super();
//        this.userId = userId;
//        this.login = login;
//        this.password = password;
//        this.passwordAgain = password;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthday = birthday;
//        this.role = role;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(final String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(final String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(final String login) {
//        this.login = login;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(final String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(final String password) {
//        this.password = password;
//    }
//
//    public String getPasswordAgain() {
//        return passwordAgain;
//    }
//
//    public void setPasswordAgain(String passwordAgain) {
//        this.passwordAgain = passwordAgain;
//    }
//
//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(final Date birthday) {
//        this.birthday = birthday;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(final Role role) {
//        this.role = role;
//    }
//
//    @Override
//    public String toString() {
//        return "UserBean [userId=" + userId + ", firstName=" + firstName + ", lastName="
//            + lastName + ", login=" + login + ", email=" + email
//            + ", password=" + password + ", birthday=" + birthday
//            + ", role=" + role + "]";
//    }

}
