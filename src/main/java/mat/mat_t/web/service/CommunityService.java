package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.web.repository.CommunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public void saveCommunity(Community community) {
        communityRepository.save(community);
    }

    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    //todo 좋아요 테이블에서 좋아요 체크 여부 검증 로직 추가해서 처음 클릭 시 좋아요 +, 재클릭 시  좋아요-로 수정
    public Community clickLike(Long id) {
        Optional<Community> optionalCommunity = communityRepository.findById(id);
        Community findCom = optionalCommunity.get();
        int findLike = findCom.getLikes();
        findCom.setLikes(++findLike);
        return findCom;
    }

    public Community edit(Long id, String title, String content) {
        Optional<Community> findCom = communityRepository.findById(id);
        Community com = findCom.get();
        com.setTitle(title);
        com.setContent(content);
        return com;
    }
    public void remove(Long id) {
        Optional<Community> findCom = communityRepository.findById(id);
        Community com = findCom.get();
        communityRepository.delete(com);
    }

    public void pressLikes(Community community){
        int likes=community.getLikes();
        likes+=1;
        community.setLikes(likes);
    }

    public void cancelLikes(Community community){
        int likes=community.getLikes();
        likes-=1;
        community.setLikes(likes);
    }

    public Community findByCommunityId(Long communityId){
        return communityRepository.findCommunityById(communityId);
    }
}
