package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.EnumClass;

import javax.persistence.*;

@Entity
@Table(name="students")
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_seq")
    @SequenceGenerator(name="student_seq",sequenceName = "instructor_seq",allocationSize = 1)
    private Long id;
    @Column(name="first_Name")
    private String firstName;
    @Column(name="last_Name")
    private String lastName;
    @Column(name="phone_Number")
    private int phoneNumber;
    @Column(unique = false)
    private String email;

    @Column(name="study_Format")
    private EnumClass studyFormat;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST, CascadeType.REFRESH})
    private Course course;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Company company;



    public Student(String firstName, String lastName, int phoneNumber, String email, EnumClass studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }
}
