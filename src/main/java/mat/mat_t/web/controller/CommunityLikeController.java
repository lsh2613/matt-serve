package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.CommunityLike;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.service.CommunityLikeService;
import mat.mat_t.web.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class CommunityLikeController {

    private final CommunityService communityService;
    private final CommunityLikeService communityLikeService;

    @ApiOperation(value = "좋아요 누르기")
    @PostMapping("community/like/{communityId}")
    public ResponseEntity pressLike(@PathVariable Long communityId
            , HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        Community community=communityService.findByCommunityId(communityId);
        CommunityLike communityLike = new CommunityLike(loginUser, community);

        if(communityLikeService.duplicate(communityId,loginUser.getId())){
            throw new IllegalStateException("이미 좋아요를 누르셨습니다.");
        }

        communityLikeService.saveLike(communityLike);
        communityService.pressLikes(community);

        return null;
    }

    @ApiOperation(value="좋아요 취소하기")
    @DeleteMapping("community/like/{communityId}")
    public ResponseEntity cancelLike(@PathVariable Long communityId, HttpServletRequest request){

        Community community=communityService.findByCommunityId(communityId);
        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        communityService.cancelLikes(community);
        communityLikeService.deleteLike(communityId, loginUser.getId());
        return null;
    }
}
