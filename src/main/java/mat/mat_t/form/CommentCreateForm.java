package mat.mat_t.form;

import lombok.Data;


@Data
public class CommentCreateForm {

    private String content;
    private String Writer;
    private Long communityId;

    public CommentCreateForm(String content, String writer, Long communityId) {
        this.content = content;
        this.Writer = writer;
        this.communityId = communityId;
    }
}
