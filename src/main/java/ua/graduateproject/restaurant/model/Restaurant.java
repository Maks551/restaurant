package ua.graduateproject.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Restaurant extends NamedEntity {
    private String address;
    private Vote vote;
    private Menu menu;

    //user_id_by_update
    private User user;

    public Restaurant(String address, Vote vote, Menu menu, User user) {
        this.address = address;
        this.vote = vote;
        this.menu = menu;
        this.user = user;
    }

    public Restaurant(Integer id, String name, String address, Vote vote, Menu menu, User user) {
        super(id, name);
        this.address = address;
        this.vote = vote;
        this.menu = menu;
        this.user = user;
    }
}
