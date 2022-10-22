package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.ClassTag;

import mat.mat_t.domain.class_.dto.ClassInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClassTagRepository extends JpaRepository<ClassTag, Long> {


    // 클래스아이디로 클래스 태그 조회
    List findDistinctClassTagByClassesCT_ClassId(@Param(value = "class_id") Long classId);

    // tagInfo 아이디로 클래스 태그 조회
    List findClassTagByTagInfo_TagInfoId(@Param(value = "tagInfo_id") Long tagInfoId);

    // tagInfo 아이디 리스트로 클래스 태그 조회

    List <ClassTag> findMemberDistinctClassTagByTagInfo_TagInfoIdIn(List<Long> tagInfoIdList);
    //중복체크
    boolean existsClassTagByClassesCT_ClassIdAndTagInfo_TagInfoId(@Param(value = "class_id") Long classId,
                                                                  @Param(value = "tagInfo_id") Long tagInfoId);
    List<ClassTag> findByTagInfo_TagInfoIdIn(List<Long> Id);

    @Query(value = "SELECT new mat.mat_t.domain.class_.dto.ClassInfoDto(c.classId,c.title,c.numberOfStudents,c.descriptions,c.place,c.startTime,c.endTime,c.startDate,c.endDate)" +
            "From ClassTag ct left join Classes c On ct.classesCT.classId=c.classId " +
            "where ct.classesCT.classId IN(:classId)  " +
            "group by ct.classesCT.classId")
    List<ClassInfoDto> findClassTag(List<Long> classId);
}