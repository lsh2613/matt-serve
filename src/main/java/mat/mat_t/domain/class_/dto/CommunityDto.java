package mat.mat_t.domain.class_.dto;

//todo 1. 카테고리 선택 시 해당 커뮤니티만 출력 -> 완료
//todo 2. 커뮤니티 전체 조회 시 댓글 개수 출력
//todo 3. 커뮤니티 하나 조회 시 댓글 같이 출력

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mat.mat_t.domain.Comment;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.user.Category;
import mat.mat_t.form.CommentForm;
import mat.mat_t.web.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CommunityDto {

    Long userId;
    String userName;

    Long communityId;
    Category category;
    String title;
    String content;
    int numOfLikes;
    int numOfComments;
    String pastTime;
    List<CommentForm> commentList;

    /**
     * flag=0 -> 커뮤니티 단일 조회 -> 관련 댓글들 출력
     * flag=1 -> 커뮤니티 복수 조회 -> 댓글 개수만 출력
     */

    public CommunityDto(Community community) {
        this.userId = community.getUserCom().getId();
        this.userName = community.getUserCom().getNickname();
        this.communityId = community.getId();
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
            this.pastTime = Integer.toString(nowYear - comYear).concat("년 전");
        else if (comMonth < nowMonthValue)
            this.pastTime = Integer.toString(nowMonthValue - comMonth).concat("개월 전");
        else if (comDay < nowDayOfMonth)
            this.pastTime = Integer.toString(nowDayOfMonth - comDay).concat("일 전");
        else if (comHour < nowHour)
            this.pastTime = Integer.toString(nowHour - comHour).concat("시간 전");
        else if (comMinute < nowMinute)
            this.pastTime = Integer.toString(nowMinute - comMinute).concat("분 전");
        else
            this.pastTime = "1분 전";

    }
}