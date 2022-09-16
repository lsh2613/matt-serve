package mat.mat_t.domain;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.User;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private int like=0;
    private String Date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id")
//    private User userCom;


    public Community() {
    }

    public Community(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
