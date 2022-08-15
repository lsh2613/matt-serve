package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.web.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public List<Instructor> findAllInstructor() {
        return instructorRepository.findAll();
    }

    public Instructor findInstructorId(Long instructorId) {
        return instructorRepository.findOne(instructorId);
    }
}
