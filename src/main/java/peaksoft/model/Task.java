package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tasks")
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_seq")
    @SequenceGenerator(name="task_seq",sequenceName = "task_seq",allocationSize = 1)
    private Long id;
    @Column(name="task_Name")
    private String taskName;
    @Column(name="task_Text")
    private String taskText;
    @Column(name="deadline")
    private String deadline;
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Lesson lesson;

    public Task(String taskName, String taskText, String deadline) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
    }
}
