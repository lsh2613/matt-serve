package mat.mat_t.domain.class_;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="wish_id")
    private Long wishId;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "class_id")
    private Classes classCs;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private User userCS;

    public Wish() {
    }

    public Wish(Classes classCs,User userCS) {
        this.setClassCs(classCs);
        this.setUserCS(userCS);
    }
}
