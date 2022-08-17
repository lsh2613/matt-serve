package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.web.repository.ClassInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassInfoService {

    private final ClassInfoRepository classInfoRepository;

    //클래스정보 생성{
    public void saveClassInfo(ClassInformation classInformation){
        classInfoRepository.save(classInformation);
    }

    //클래스정보 수정
    public void updateClassInfo(ClassInformation upClassInfo, Long codeId){
        ClassInformation classInformation = classInfoRepository.findById(codeId).get();
        classInformation.update(upClassInfo);
    }

    //클래스정보 삭제
    public void deleteClassInfo(Long codeId){
        classInfoRepository.deleteById(codeId);
    }


    //클래시 정보 전체 조회
    public List<ClassInformation> findAllCodeInfo() {
        return classInfoRepository.findAll();
    }

    //클래스 정보 단건 조회
    public ClassInformation findById(long codeId) {
        return classInfoRepository.findById(codeId).orElse(null);
    }
}
