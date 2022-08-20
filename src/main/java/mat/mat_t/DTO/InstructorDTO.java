package mat.mat_t.DTO;

import lombok.Data;

import java.util.List;

@Data
public class InstructorDTO {

    private Long instructorId;
    private String major;

    private List<ClassDTO> classes;

    public InstructorDTO(Long instructorId, String major) {
        this.instructorId = instructorId;
        this.major = major;
    }
}
