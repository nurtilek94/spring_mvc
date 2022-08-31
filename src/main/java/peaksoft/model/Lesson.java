package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="lessons")
@NoArgsConstructor
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "lesson_seq")
    @SequenceGenerator(name="lesson_seq",sequenceName = "lesson_seq",allocationSize = 1)
    private Long id;
    @Column(name="lesson_Name")
    private String lessonName;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,
            CascadeType.MERGE,CascadeType.REFRESH})
    private Course course;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lesson")
    private List<Task> tasks;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "lesson")
    private Video video;

    public void addTasks(Task task) {
        if(tasks == null) {
            tasks = new ArrayList<>();
        } else {
            this.tasks.add(task);
        }
    }
    public void addVideo(Video video) {
        System.out.println(video);
    }

    public Lesson(String lessonName) {
        this.lessonName = lessonName;
    }
}
