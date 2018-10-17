package ua.graduateproject.restaurant.to;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static ua.graduateproject.restaurant.model.AbstractNamedEntity.MAX_SIZE_NAME;
import static ua.graduateproject.restaurant.util.UserUtil.MAX_SIZE_EMAIL;
import static ua.graduateproject.restaurant.util.UserUtil.MAX_SIZE_PASSWORD;
import static ua.graduateproject.restaurant.util.UserUtil.MIN_SIZE_PASSWORD;

public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = MAX_SIZE_NAME)
    private String name;

    @Email
    @NotBlank
    @Size(max = MAX_SIZE_EMAIL)
    private String email;

    @Size(min = MIN_SIZE_PASSWORD, max = MAX_SIZE_PASSWORD)
    private String password;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
