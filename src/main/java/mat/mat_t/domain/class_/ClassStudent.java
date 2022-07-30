package mat.mat_t.domain.class_;

import javax.persistence.Entity;

@Entity
public class ClassStudent {

    private int classId;
    private String studentId;
    private String content; // 수강 신청 시 메시지 전달 목적
}
