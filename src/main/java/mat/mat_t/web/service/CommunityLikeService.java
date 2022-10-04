package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.CommunityLike;
import mat.mat_t.domain.review.ReviewLike;
import mat.mat_t.web.repository.CommunityLikeRepository;
import mat.mat_t.web.repository.ReviewLikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityLikeService {

    private final CommunityLikeRepository communityLikeRepository;

    public void saveLike(CommunityLike communityLike){
        communityLikeRepository.save(communityLike);
    }

    public boolean duplicate(Long communityId,Long userId){
        return communityLikeRepository.existsCommunityLikeByCommunity_IdAndUserCS_Id(communityId,userId);
    }

    public void deleteLike(Long communityId,Long userId){
        communityLikeRepository.deleteCommunityLikeByCommunity_IdAndUserCS_Id(communityId,userId);
    }

    public void deleteByCommunity(Long communityId){
        List<CommunityLike> communityLikes=communityLikeRepository.findCommunityLikeByCommunity_Id(communityId);
        communityLikeRepository.deleteAll(communityLikes);
    }

}
