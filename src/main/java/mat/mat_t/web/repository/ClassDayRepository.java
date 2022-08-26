package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.ClassDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDayRepository extends JpaRepository<ClassDay, Long> {

    //클래스아이디로 클래스데이 조회
    List findClassDayByClassesD_ClassId(@Param(value = "class_id") Long classId);

    //day 아이디로 클래스데이 조회
    List findClassDayByDays_DayId(@Param(value = "day_id") Long dayId);

    //중복체크
    boolean existsClassDayByClassesD_ClassIdAndDays_DayId(@Param(value = "class_id") Long classId, @Param(value = "day_id") Long dayId);
}
