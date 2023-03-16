package model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class User extends BaseEntity {
    protected String name;
    protected String surname;
    protected String phone;
    protected String email;
}
