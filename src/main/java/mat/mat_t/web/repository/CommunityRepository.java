package mat.mat_t.web.repository;

import mat.mat_t.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
        Community findCommunityById(Long Id);
}
