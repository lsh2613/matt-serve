package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.CommunityForm;
import mat.mat_t.web.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CommunityController {


    private CommunityService communityService;

    @ApiOperation("커뮤니티 생성")
    @PostMapping("/community/add")
    public ResponseEntity addCommunity(@Valid @RequestBody CommunityForm form,
                                       BindingResult bindingResult,
                                       HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        Community community = new Community(form.getTitle(), form.getContent());

        communityService.saveCommunity(community);
        return ResponseEntity.ok(community);
    }

    @ApiOperation("커뮤니티 전체 조회")
    @GetMapping("/community")
    public ResponseEntity findAllCommunity() {
        List<Community> all = communityService.findAll();
        return ResponseEntity.ok(all);
    }

    @ApiOperation("커뮤니티 수정")
    @PatchMapping("/community/edit")
    public ResponseEntity editCommunity(@RequestParam Long communityId,
                                        @Valid @RequestBody CommunityForm form) {
        Community editCom = communityService.edit(communityId, form.getTitle(), form.getContent());
        return ResponseEntity.ok(editCom);
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

        String title;
        String content;
        String pastTime; //몇 분 이전에 작성됐는지


    }

}
