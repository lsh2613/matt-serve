package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface WishRepository extends JpaRepository<Wish,Long> {

    boolean existsByClassCs_ClassIdAndUserCS_Id(Long classId,Long userId);
    List<Wish> findWishByUserCS_Id(Long userId);
    @Transactional
    void deleteWishByClassCs_ClassIdAndUserCS_Id(Long classId,Long userId);
}
