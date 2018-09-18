package ua.graduateproject.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class Vote extends AbstractBaseEntity {
    private int restaurantId;
    private int userId;
    private LocalDateTime dateTime;

    public Vote(int restaurantId, int userId, int votes, LocalDateTime dateTime){
        this(null, restaurantId, userId, votes, dateTime);
    }

    public Vote(Integer id, int restaurantId, int userId, int votes, LocalDateTime dateTime){
        super(id);
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.dateTime = dateTime;
    }
}
