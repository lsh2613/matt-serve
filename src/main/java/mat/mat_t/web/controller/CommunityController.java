package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.CommunityForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CommunityController {


    private final CommunityService communityService;

    @ApiOperation("커뮤니티 생성")
    @PostMapping("/community/add")
    public ResponseEntity addCommunity(@Valid @RequestBody CommunityForm form,
                                       BindingResult bindingResult,
                                       HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        Community community = new Community(form.getTitle(), form.getContent());
        community.setUserCom(loginUser);
        communityService.saveCommunity(community);

        CommunityDto communityDto = new CommunityDto(community);

        return ResponseEntity.ok(communityDto);
    }
    
    @ApiOperation("커뮤니티 전체 조회")
    @GetMapping("/community")
    public ResponseEntity findAllCommunity() {
        List<Community> communityList = communityService.findAll();

        List<CommunityDto> communityDto = communityList.stream()
                .map(c -> new CommunityDto(c))
                .collect(Collectors.toList());

        return ResponseEntity.ok(communityDto);
    }

    @ApiOperation("커뮤니티 수정")
    @PatchMapping("/community/edit")
    public ResponseEntity editCommunity(@RequestParam Long communityId,
                                        @Valid @RequestBody CommunityForm form) {
        Community editCom = communityService.edit(communityId, form.getTitle(), form.getContent());
        CommunityDto communityDto = new CommunityDto(editCom);
        return ResponseEntity.ok(communityDto);
    }

    @ApiOperation("커뮤니티 삭제")
    @PatchMapping("/community/delete")
    public ResponseEntity deleteCommunity(@RequestParam Long communityId) {
        communityService.remove(communityId);
        return ResponseEntity.ok("커뮤니티 삭제 완료");
    }

    //todo 좋아요 테이블 유저id, 커뮤니티id, 좋아요 누름 여부 flag 생성 후 flag 체크 생성
    @ApiOperation("좋아요 클릭")
    @PostMapping("/community/clickLike")
    public ResponseEntity plusLike(@RequestParam Long communityId) {
        Community community = communityService.clickLike(communityId);
        return ResponseEntity.ok(community);
    }

    @Getter
    @Setter
    static class CommunityDto {
        Long userId;
        String userName;

        String category;
        String title;
        String content;
        int numOfLikes;
        int numOfComments;

        String pastTime; //몇 분 이전에 작성됐는지

        public CommunityDto(Community community) {
            this.userId = community.getUserCom().getId();
            this.userName = community.getUserCom().getName();
            this.title = community.getTitle();
            this.content = community.getContent();
            this.pastTime = community.getContent();
        }
    }

}
