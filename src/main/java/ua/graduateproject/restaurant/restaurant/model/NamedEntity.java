package ua.graduateproject.restaurant.restaurant.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class NamedEntity extends BaseEntity {
    protected String name;

    public boolean isNew(){
        return this.name == null;
    }

    protected NamedEntity(Integer id, String name){
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, name);
    }
}
