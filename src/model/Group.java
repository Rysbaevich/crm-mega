package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
public class Group extends BaseEntity {
    private String name;
    private String room;
    private LocalTime startTime;
    private Mentor mentor;
    private Course course;
    private List<Student> students;
}
