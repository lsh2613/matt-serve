package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.web.repository.ClassInfoRepository;
import mat.mat_t.web.repository.ClassRepository;
import mat.mat_t.web.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;
    private final InstructorRepository instructorRepository;
    private final ClassInfoRepository classInfoRepository;

    /**클래스 생성**/
    @Transactional
    public Long create(Long codeId, Long instructorId, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime, String days, String category, Long date){

        validateDuplicateClass(codeId, instructorId); //중복 클래스 검증

        //엔티티 조회
        ClassInformation classInformation = classInfoRepository.findOne(codeId);
        Instructor instructor = instructorRepository.findOne(instructorId);


        //주문 생성
        Classes classes = Classes.createClass(classInformation, instructor, title, numberOfStudents, descriptions, place, startTime, endTime, days, category, date);

        //주문 저장
        classRepository.save(classes);
        return classes.getClassId();
    }

    /***중복 클래스 검사*/
    private void validateDuplicateClass(Long codeId, Long instructorId) {
        List<Classes> findClasses =   classRepository.findSameClass(codeId, instructorId);
        if (!findClasses.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 클래스입니다."); }
    }

    /**전체 클래스 조회**/
    public List<Classes> findClasses() {
        return classRepository.findAll();
    }

    /**클래스 개별 조회**/
    public Classes findOne(Long classId) {
        return classRepository.findOne(classId);
    }

    /**클래스 수정**/
    //수정하는 방법이 이게 맞는지 확실하지 않음,,어렵,,
    public void updateClass(Classes classes){
        classRepository.save(classes);
    }

    /**클래스 삭제**/

}
