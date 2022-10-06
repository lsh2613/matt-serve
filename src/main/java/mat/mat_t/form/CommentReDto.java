package mat.mat_t.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mat.mat_t.domain.Comment;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentReDto {
    private Long commentId;
    private String content;
    private User user;
    private Community community;

    /* Dto -> Entity */
    public Comment toEntity() {
        Comment comments = Comment.builder()
                .commentId(commentId)
                .content(content)
                .userComment(user)
                .community(community)
                .build();
        return comments;
    }
}