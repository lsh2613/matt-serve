package mat.mat_t.web.repository;

import mat.mat_t.domain.Community;
import mat.mat_t.domain.user.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
        Community findCommunityById(Long Id);

        List<Community> findCommunitiesByCategory(Category category);
}
