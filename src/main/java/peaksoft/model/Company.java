package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="companies")
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "company_seq")
    @SequenceGenerator(name = "company_seq",sequenceName = "company_seq",allocationSize = 1)
    private Long id;
    @Column(name="Company_name")
    private String companyName;
    @Column(name="located_Country")
    private String locatedCountry;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Course>courses;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Student>students;
    public void addStudent(Student student){
        if (students==null){
            students=new ArrayList<>();
        }
        students.add(student);
    }

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }
    public void addCourse(Course course){
        if(courses==null){
            courses=new ArrayList<>();
        }
        courses.add(course);
    }
    public void addInstructor(Instructor instructor){
        if(instructors==null){
            instructors=new ArrayList<>();
        }
        instructors.add(instructor);
    }
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Instructor> instructors;
}
