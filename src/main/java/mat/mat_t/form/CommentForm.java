package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.Comment;

@Data
public class CommentForm {

    private Long commentId;
    private String content;
    private String Writer; // 댓글 작성자 닉네임
    private Long communityId;
    private Long userId; // 댓글 작성자 고유 id

    /* Entity -> Dto */
    public CommentForm(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.Writer = comment.getUserComment().getNickname();
        this.communityId = comment.getCommunity().getId();
        this.userId = comment.getUserComment().getId();
    }

    public CommentForm() {
    }
}
