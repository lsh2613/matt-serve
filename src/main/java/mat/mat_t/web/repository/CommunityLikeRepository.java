package mat.mat_t.web.repository;

import mat.mat_t.domain.CommunityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
        boolean existsCommunityLikeByCommunity_IdAndUserCS_Id(Long communityId,Long userId);
        List<CommunityLike> findCommunityLikeByCommunity_Id(Long communityId);

        @Transactional
        void deleteCommunityLikeByCommunity_IdAndUserCS_Id(Long communityId,Long userId);
}
