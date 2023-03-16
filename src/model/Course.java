package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter

public class Course extends BaseEntity {
    private String name;
    private double price;
    private CourseFormat courseFormat;
}
