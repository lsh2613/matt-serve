package mat.mat_t.domain.class_;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.metrics.buffering.StartupTimeline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Class {

    @Id
    @GeneratedValue
    @Column(name = "class_id")
    private Long classId;

    @Id
    @GeneratedValue
    @Column(name = "instructor_id")
    private Long instructorId;


    private String title;
    private int numberOfStudents;
    private String descriptions;
    private String place;
//    private ?? startTime;
//    private ?? endTime;
//    private ?? days;
//    private ?? category;
//    private ?? date;
}
