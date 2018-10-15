package ua.graduateproject.restaurant.util;

import ua.graduateproject.restaurant.model.Role;
import ua.graduateproject.restaurant.model.User;
import ua.graduateproject.restaurant.to.UserTo;

public class UserUtil {
    public static final int MAX_SIZE_EMAIL = 100;
    public static final int MIN_SIZE_PASSWORD = 5;
    public static final int MAX_SIZE_PASSWORD = 100;

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
