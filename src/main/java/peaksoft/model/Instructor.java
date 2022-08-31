package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructors")
@NoArgsConstructor
@Getter
@Setter
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "instructor_seq")
    @SequenceGenerator(name="instructor_seq",sequenceName = "instructor_seq",allocationSize = 1)
    private Long id;
    @Column(name="first_Name")
    private String firstName;
    @Column(name="last_Name")
    private String lastName;
    @Column(name="phone_Number")
    private int phoneNumber;

    private String email;
    @Column(name="specialization")
    private String specialization;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable
    private List<Course>courses;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Company company;


    public Instructor(String firstName, String lastName, int phoneNumber, String email, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
    }
    public void addCourse(Course course){
        if(courses==null){
            courses=new ArrayList<>();
        }courses.add(course);
    }
}
