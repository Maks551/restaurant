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

    @Column(name = "user_id", nullable = false)
    @NotNull
    private Integer userId;

    @Column(name = "restaurant_id", nullable = false)
    @NotNull
    private Integer restaurantId;

    @Column(name = "vote", nullable = false)
    @NotNull
    private int vote;

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime dateTime;

    public Vote(Vote vote) {
        this(vote.id, vote.userId, vote.restaurantId, vote.dateTime, vote.vote);
    }

    public Vote(int userId, int restaurantId, LocalDateTime dateTime, int vote){
        this(null, restaurantId, userId, dateTime, vote);
    }

    public Vote(Integer id, Integer userId, Integer restaurantId, LocalDateTime dateTime, Integer vote){
        super(id);
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.dateTime = dateTime;
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                ", dateTime=" + dateTime +
                ", vote=" + vote +
                '}';
    }
}
