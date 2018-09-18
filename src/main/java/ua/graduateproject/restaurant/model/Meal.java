package ua.graduateproject.restaurant.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "calories", nullable = false)
    @NotBlank
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Menu menu;

    public Meal(String description, int calories){
        this(null, description, calories);
    }

    public Meal(Integer id, String description, int calories){
        super(id);
        this.description = description;
        this.calories = calories;
    }
}
