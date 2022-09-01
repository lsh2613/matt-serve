package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.ClassStudents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStudentsRepository extends JpaRepository<ClassStudents, Long> {

  // userid와 classid를 이용한 where 조건 쿼리를 만들기 위함
  // jpa는 이렇게 메소드를 생성해주는 것으로 쿼리를 자동 생성해줌
  ClassStudents findByUserCS_IdAndClassesCS_ClassId(Long userId, Long classId);
}