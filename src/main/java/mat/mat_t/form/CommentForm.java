package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.Comment;


@Data
public class CommentForm {

    private Long commentId;
    private String content;
    private String Writer;
    private Long communityId;

    /* Entity -> Dto*/
    public CommentForm(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.Writer = comment.getUserComment().getNickname();
        this.communityId = comment.getCommunity().getId();
    }

    public CommentForm() {
    }
}
