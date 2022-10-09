package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.InstructorForm;
import mat.mat_t.web.repository.InstructorRepository;
import mat.mat_t.web.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    //강사 생성
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
    public List<InstructorForm> findInstructorWithScore(){
        return instructorRepository.findAllByInstructorIdAndClassList();
    }

    //강사 단건 조회
    public List<InstructorForm> findById(Long instructorId) {
        return instructorRepository.findByInstructorIdAndAndClassList(instructorId);
    }

    //강사 중복 생성 금지
    public void hasDuplicateInstructor(Long id) {
        List<User> findInstructors = instructorRepository.findByUserIn(id);
        System.out.println(findInstructors);
        if (!findInstructors.isEmpty()) {
            throw new IllegalStateException("이미 강사 신청을 완료하였습니다.");
        }
    }

}
