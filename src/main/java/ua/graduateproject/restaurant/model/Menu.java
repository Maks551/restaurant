package ua.graduateproject.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Meal> meals;

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "menu")
    @Column(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Menu(LocalDateTime dateTime, Restaurant restaurant) {
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Menu(Integer id, LocalDateTime dateTime, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setMeals(Collection<Meal> meals) {
        this.meals = CollectionUtils.isEmpty(meals) ? Collections.emptyList() : new ArrayList<>(meals);
    }
}
