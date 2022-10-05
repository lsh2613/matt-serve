package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.form.ClassForm;
import mat.mat_t.web.repository.ClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;

    @Temporal(TemporalType.DATE)
    private Date now = new Date();

    // 클래스 생성{
    public void saveClass(Classes classes) {
        classRepository.save(classes);
    }

    // 클래스 수정
    public void updateClass(Classes upClasses, Long ClassId) {
        Classes classes = classRepository.findById(ClassId).get();
        classes.update(upClasses);
    }

    // 클래스 삭제
    public void deleteClass(Long ClassId) {
        classRepository.deleteById(ClassId);
    }

    // 클래스 단건 조회 <--기존 버전 (다른 코드에서 사용됨)
    public Classes findById(Long classId) {
        return classRepository.findById(classId).orElse(null);
    }

    // 클래스 단건 조회 <-- waitingStudent 수 추가한 버전
    public ClassForm findByClassId(Long classId) {
        return classRepository.findAllByClassId(classId);
    }

    // 전체 클래스 조회
    public List<ClassForm> findAllClass() {
        return classRepository.findAllByWaitingStudents();
    }

    // 클래스 강사 아이디로 조회
    public List<ClassForm> findByInstructorId(Long instructorId) {
        return classRepository.findAllByInstructorC_InstructorId(instructorId);
    }

    // 진행 전 클래스 조회
    public List<ClassForm> findBefore() {
        return classRepository.findAllByStartDateBefore(now);
    }

    // 진행 중 클래스 조회
    public List<ClassForm> findNow() {
        return classRepository.findAllByStartDateAfterAndEndDateBefore(now);
    }

    // 진행 완료 클래스 조회
    public List<ClassForm> findAfter() {
        return classRepository.findAllByEndDateAfter(now);
    }

    // 요일로 클래스 조회(해당 클래스들 정보 모두 조회)
    public List<ClassForm> findByDayName(String dayName) {
        return classRepository.findAllByClassDays(dayName);
    }

    // 키워드로 클래스 조회(title, category, description, place 에서 검색)
    public List<ClassForm> findByKeyword(String keyword) {
        return classRepository.findAllByTitleOrCategoryOrDescriptionsOrPlace(keyword);
    }

    public Classes findClassByClassId(Long classId){
        return classRepository.findByClassId(classId);
    }
}
