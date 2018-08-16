package ua.graduateproject.restaurant.restaurant.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
public class Meal extends BaseEntity {
    private LocalDateTime dateTime;
    private String description;
    private int calories;
    private int restaurant_id;

    public Meal(LocalDateTime dateTime, String description, int calories){
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories){
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDate getDate(){
        return dateTime.toLocalDate();
    }
    public LocalTime getTime(){
        return dateTime.toLocalTime();
    }
}
