package ua.graduateproject.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "meals")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("dateTime DESC")
    private Set<Meal> meals;

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Menu(Set<Meal> meals, LocalDateTime dateTime, Restaurant restaurant) {
        this.meals = meals;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Menu(Integer id, Set<Meal> meals, LocalDateTime dateTime, Restaurant restaurant) {
        super(id);
        this.meals = meals;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public void setMenu(Collection<Meal> meals){
        this.meals = CollectionUtils.isEmpty(meals) ? Collections.emptySet() : new HashSet<>(meals);
    }
}
