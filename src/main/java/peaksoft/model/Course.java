package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="courses")
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_seq")
    @SequenceGenerator(name = "course_seq",sequenceName = "course_seq",allocationSize = 1)
    private Long id;
    @Column(name="course_name")
    private String courseName;

    @Column(name="data_Of_Start")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfStart;
    @Column(name="duration")
    private int duration;
    @Column(name="image")
    private String image;
    @Column(name="description")
    private String description;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.PERSIST,CascadeType.REFRESH})
    private Company company;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course",fetch = FetchType.EAGER)
    private List<Student>students;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.REFRESH,CascadeType.PERSIST},mappedBy = "courses")
    private List<Instructor>instructors;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<Lesson>lessons;

    public Course(String courseName, LocalDate dateOfStart, int duration, String image, String description) {
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.duration = duration;
        this.image = image;
        this.description = description;
    }
    public void addInstructor(Instructor instructor){
        if(instructors==null){
            instructors=new ArrayList<>();
        }instructors.add(instructor);
    }

    public void addLesson(Lesson lesson){
        if (lessons==null){
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
    }
}
