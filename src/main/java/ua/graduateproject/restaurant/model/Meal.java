package ua.graduateproject.restaurant.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ua.graduateproject.restaurant.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames =  "restaurant_id",name = "meal_restaurant_idx")})
public class Meal extends AbstractBaseEntity {

    @Column(name = "description", nullable = false)
    @Size(min = 2, max = 120)
    @NotBlank
    private String description;

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 1000)
    @NotNull
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    public Meal(String description, int price){
        this(null, description, price);
    }

    public Meal(Integer id, String description, int price){
        super(id);
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
