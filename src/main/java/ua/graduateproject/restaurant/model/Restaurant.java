package ua.graduateproject.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ua.graduateproject.restaurant.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static ua.graduateproject.restaurant.util.DateTimeUtil.DATE_TIME_PATTERN;

@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "address", name = "restaurant_unique_address_idx"),
                                    @UniqueConstraint(columnNames = "date_of_add_menu", name = "restaurant_unique_menu_date_idx")})
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    private String address;

    @Column(name = "date_of_add_menu", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime dateTimeOfAddMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Meal> menu;

    public Restaurant(Restaurant r) {
        this(r.id, r.name, r.dateTimeOfAddMenu, r.address);
//        setUser(r.user);
    }

    public Restaurant(String name, LocalDateTime dateTimeOfAddMenu, String address){
        this(null, name, dateTimeOfAddMenu, address);
    }

    public Restaurant(Integer id, String name, LocalDateTime dateTimeOfAddMenu, String address) {
        super(id, name);
        this.address = address;
        this.dateTimeOfAddMenu = dateTimeOfAddMenu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateTimeOfAddMenu=" + dateTimeOfAddMenu +
                '}';
    }
}
