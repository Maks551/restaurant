package ua.graduateproject.restaurant.to;

import lombok.Getter;
import lombok.ToString;
import ua.graduateproject.restaurant.model.Meal;
import ua.graduateproject.restaurant.model.Restaurant;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@ToString
public class ActualMenu {
    private Restaurant restaurant;
    private Set<Meal> meals;
    private LocalDateTime dateTime;
    private boolean actual;

    public ActualMenu(Restaurant restaurant, Set<Meal> meals, LocalDateTime dateTime, boolean actual) {
        this.restaurant = restaurant;
        this.meals = meals;
        this.dateTime = dateTime;
        this.actual = actual;
    }
}
