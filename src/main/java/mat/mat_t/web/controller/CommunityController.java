package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mat.mat_t.domain.Comment;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.class_.dto.CommunityDto;
import mat.mat_t.domain.user.Category;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.CommentForm;
import mat.mat_t.form.CommunityForm;
import mat.mat_t.web.service.CommentService;
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
    private final CommentService commentService;
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
        List<CommentForm> listcomments = commentService.Listcomments(communityId);
        communityDto.setCommentList(listcomments);
        return ResponseEntity.ok(communityDto);
    }

    @ApiOperation("커뮤니티 전체 조회")
    @GetMapping("/community")
    public ResponseEntity findAllCommunity() {
        List<Community> communityList = communityService.findAll();

        List<CommunityDto> communityDto = communityList.stream()
                .map(c -> getCommunityDto(c))
                .collect(Collectors.toList());
        return ResponseEntity.ok(communityDto);
    }

    @ApiOperation("커뮤니티 카테고리로 조회")
    @GetMapping("/community/comByCategory")
    public ResponseEntity findCommunitiesByCategory(@RequestParam Category category) {
        List<Community> communityList = communityService.findByCategory(category);

        List<CommunityDto> communityDto = communityList.stream()
                .map(c -> getCommunityDto(c))
                .collect(Collectors.toList());

        return ResponseEntity.ok(communityDto);
    }

    private CommunityDto getCommunityDto(Community c) {
        CommunityDto communityDto = new CommunityDto(c);
        communityDto.setNumOfComments(commentService.Listcomments(c.getId()).size());
        return communityDto;
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



}
