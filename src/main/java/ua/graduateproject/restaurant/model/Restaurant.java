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
import java.time.LocalTime;

import static ua.graduateproject.restaurant.util.DateTimeUtil.TIME_PATTERN;

@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    private String address;

    @Column(name = "open_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = TIME_PATTERN)
    private LocalTime openTime;

    @Column(name = "close_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = TIME_PATTERN)
    private LocalTime closeTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private User user;

    public Restaurant(String address, Menu menu, User user) {
        this.address = address;
        this.menu = menu;
        this.user = user;
    }

    public Restaurant(Integer id, String name, String address, Menu menu, User user) {
        super(id, name);
        this.address = address;
        this.menu = menu;
        this.user = user;
    }
}
