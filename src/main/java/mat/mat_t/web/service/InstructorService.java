package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.web.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    //강사 생성{
    public void saveInstructor(Instructor instructor){
        instructorRepository.save(instructor);
    }

    //강사 수정
    public void updateInstructor(Instructor upInstructor, Long instructorId){
        Instructor instructor = instructorRepository.findById(instructorId).get();
        instructor.update(upInstructor);
    }

    //강사 삭제
    public void deleteInstructor(Long instructorId){
        instructorRepository.deleteById(instructorId);
    }

    //전체 강사 조회
    public List<Instructor> findAllInstructor() {
        return instructorRepository.findAll();
    }

    //강사 단건 조회
    public Instructor findById(Long instructorId) {
        return instructorRepository.findById(instructorId).orElse(null);
    }
}
