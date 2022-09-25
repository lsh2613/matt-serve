package mat.mat_t.domain;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.User;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "community_id")
    private Long id;
    private String title;
    private String content;
    private int likes=0;
    private String Date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id")
//    private User userCom;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();


    public Community() {
    }

    public Community(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Community(Long communityId) {
        this.id = communityId;
    }
}
