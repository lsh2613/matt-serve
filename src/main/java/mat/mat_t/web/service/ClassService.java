package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.web.repository.ClassInfoRepository;
import mat.mat_t.web.repository.ClassRepository;
import mat.mat_t.web.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;


    //클래스 생성{
    public void saveClass(Classes classes){
        classRepository.save(classes);
    }

    //클래스 수정
    public void updateClass(Classes upClasses, Long ClassId){
        Classes classes = classRepository.findById(ClassId).get();
        classes.update(upClasses);
    }

    //클래스 삭제
    public void deleteClass(Long ClassId){
        classRepository.deleteById(ClassId);
    }

    //중복 클래스 검사
    public void validateDuplicateClass(ClassInformation codeId,Instructor instructorId){
        List<Classes> findClasses =   classRepository.findByClassInformationAndInstructorC(codeId, instructorId);
        if (!findClasses.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 클래스입니다."); }
    }

    //클래스 단건 조회
    public Classes findById(long classId) {
        return classRepository.findById(classId).orElse(null);
    }

    //전체 클래스 조회
    public List<Classes> findAllClass() {
        return classRepository.findAll();
    }


}
