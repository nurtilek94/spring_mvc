package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="videos")
@NoArgsConstructor
@Getter
@Setter
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "video_seq")
    @SequenceGenerator(name="video_seq",sequenceName = "video_seq",allocationSize = 1)
    private Long id;
    @Column(name="video_Name")
    private String videoName;
    @Column(name="link")
    private String link;
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.PERSIST,CascadeType.REFRESH})
    private Lesson lesson;

    public Video(String videoName, String link) {
        this.videoName = videoName;
        this.link = link;
    }
}
