package ua.graduateproject.restaurant.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {
    public static final int MAX_SIZE_NAME = 100;

    @NotBlank
    @Size(min = 2, max = MAX_SIZE_NAME)
    @Column(name = "name", nullable = false)
    protected String name;

    protected AbstractNamedEntity(Integer id, String name){
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, name);
    }
}
