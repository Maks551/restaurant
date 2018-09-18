package ua.graduateproject.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static ua.graduateproject.restaurant.util.DateTimeUtil.DATE_TIME_PATTERN;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @Column(name = "restaurant_id", nullable = false)
    @NotNull
    private int restaurantId;

    @Column(name = "user_id", nullable = false)
    @NotNull
    private int userId;

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
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
