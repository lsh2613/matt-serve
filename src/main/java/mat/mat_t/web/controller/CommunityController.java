package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.user.Category;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.CommunityForm;
import mat.mat_t.web.service.CommunityLikeService;
import mat.mat_t.web.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final CommunityLikeService communityLikeService;

    @ApiOperation("커뮤니티 생성")
    @PostMapping("/community/add")
    public ResponseEntity addCommunity(@Valid @RequestBody CommunityForm form,
                                       BindingResult bindingResult,
                                       HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        Community community = new Community(form.getTitle(), form.getContent(), form.getCategory());
        community.setUserCom(loginUser);
        communityService.saveCommunity(community);

        CommunityDto communityDto = new CommunityDto(community);

        return ResponseEntity.ok(communityDto);
    }

    @ApiOperation("커뮤니티 하나 조회")
    @GetMapping("/community/{communityId}")
    public ResponseEntity findCommunity(@PathVariable Long communityId) {
        Community community = communityService.findByCommunityId(communityId);
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

    @ApiOperation("커뮤니티 카테고리로 조회")
    @GetMapping("/community/comByCategory")
    public ResponseEntity findCommunitiesByCategory(@RequestParam Category category) {
        List<Community> communityList = communityService.findByCategory(category);

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
        communityLikeService.deleteByCommunity(communityId);
        communityService.remove(communityId);
        return ResponseEntity.ok("커뮤니티 삭제 완료");
    }

    //todo 1. 카테고리 선택 시 해당 커뮤니티만 출력
    //todo 2. 커뮤니티 전체 조회 시 댓글 개수 출력
    //todo 3. 커뮤니티 하나 조회 시 댓글 같이 출력

    @Getter
    @Setter
    static class CommunityDto {
        Long userId;
        String userName;

        Category category;
        String title;
        String content;
        int numOfLikes;
        int numOfComments;
        String pastTime;

        public CommunityDto(Community community) {
            this.userId = community.getUserCom().getId();
            this.userName = community.getUserCom().getName();
            this.title = community.getTitle();
            this.content = community.getContent();
            this.numOfLikes = community.getLikes();
            this.category = community.getCategory();

            String communityDate = community.getDate();
            int comYear = Integer.parseInt(communityDate.substring(0, 4));
            int comMonth = Integer.parseInt(communityDate.substring(5, 7));
            int comDay = Integer.parseInt(communityDate.substring(8, 10));
            int comHour = Integer.parseInt(communityDate.substring(11, 13));
            int comMinute = Integer.parseInt(communityDate.substring(15, 17));

            LocalDateTime now = LocalDateTime.now();
            int nowYear = now.getYear();
            int nowMonthValue = now.getMonthValue();
            int nowDayOfMonth = now.getDayOfMonth();
            int nowHour = now.getHour();
            int nowMinute = now.getMinute();

            if (comYear < nowYear)
                this.pastTime = Integer.toString(nowYear - comYear ).concat("년 전");
            else if (comMonth < nowMonthValue)
                this.pastTime = Integer.toString(nowMonthValue - comMonth ).concat("개월 전");
            else if (comDay < nowDayOfMonth)
                this.pastTime = Integer.toString(nowDayOfMonth - comDay ).concat("일 전");
            else if (comHour < nowHour)
                this.pastTime = Integer.toString(nowHour - comHour).concat("시간 전");
            else if (comMinute < nowMinute)
                this.pastTime = Integer.toString(nowMinute - comMinute ).concat("분 전");
            else
                this.pastTime = "1분 전";

        }
    }

}
