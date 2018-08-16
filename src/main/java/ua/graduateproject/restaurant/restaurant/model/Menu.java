package ua.graduateproject.restaurant.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Menu extends NamedEntity {
    private Restaurant restaurant;
    private Set<Meal> meals;
    private LocalDateTime dateTime;


    public void setMenu(Collection<Meal> meals){
        this.meals = CollectionUtils.isEmpty(meals) ? Collections.emptySet() : new HashSet<>(meals);
    }
}
