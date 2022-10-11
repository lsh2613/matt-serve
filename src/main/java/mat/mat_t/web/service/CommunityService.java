package mat.mat_t.web.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.user.Category;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private final CommunityRepository communityRepository;

    public void saveCommunity(Community community) {
        communityRepository.save(community);
    }

    public List<Community> findAll() {
        return communityRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
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

    public List<Community> findByCategory(Category category) {
        List<Community> communitiesByCategory = communityRepository.findCommunitiesByCategory(category);
        return communitiesByCategory;
    }

    public List<Community> findByUser(User user) {
        List<Community> communitiesByUser = communityRepository.findCommunitiesByUserCom(user);
        return communitiesByUser;
    }

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }
}
