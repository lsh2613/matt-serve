package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
public class CommentForm {

    private Long commentId;
    private String content;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String Writer;
    private Long communityId;

    /* Entity -> Dto*/
    public CommentForm(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.Writer = comment.getUserComment().getNickname();
        this.communityId = comment.getCommunity().getId();
    }

    public CommentForm() {
    }
}
