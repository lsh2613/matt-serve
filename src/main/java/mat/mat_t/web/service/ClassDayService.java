package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassDay;
import mat.mat_t.web.repository.ClassDayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassDayService {

    private final ClassDayRepository classDayRepository;

    //클래스데이 생성
    public void saveClassDay(ClassDay classDay) {
        classDayRepository.save(classDay);
    }


    //클래스데이 삭제
    public void deleteClassDay(Long ClassDayId){
        classDayRepository.deleteById(ClassDayId);
    }

    //클래스아이디로 클래스데이 조회
    public List<ClassDay> findClassDayByClassId(Long classId) {
        return classDayRepository.findClassDayByClassesD_ClassId(classId);
    }

    //day 아이디로 클래스데이 조회
    public List<ClassDay> findClassDayByDayId(Long dayId) {
        return classDayRepository.findClassDayByDays_DayId(dayId);
    }

    //클래스 && day 둘 다 중복값이면 => 클래스데이 중복 ---> 중복값 저장안되도록 처리
    public boolean checkClassesAndDaysDuplicate(Long classId, Long dayId){
        return classDayRepository.existsClassDayByClassesD_ClassIdAndDays_DayId(classId, dayId);
    }
}
