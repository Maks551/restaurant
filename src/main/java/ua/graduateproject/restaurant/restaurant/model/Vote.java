package ua.graduateproject.restaurant.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Vote extends BaseEntity {
    private int restaurantId;
    private int userId;
    private int votes;
    private LocalDateTime dateTime;

    public Vote(int restaurantId, int userId, int votes, LocalDateTime dateTime){
        this(null, restaurantId, userId, votes, dateTime);
    }

    public Vote(Integer id, int restaurantId, int userId, int votes, LocalDateTime dateTime){
        super(id);
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.votes = votes;
        this.dateTime = dateTime;
    }
}
