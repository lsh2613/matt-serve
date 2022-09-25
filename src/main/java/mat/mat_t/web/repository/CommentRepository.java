package mat.mat_t.web.repository;

import mat.mat_t.domain.Comment;
import mat.mat_t.form.CommentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c from Comment c where c.community.id=:communityId and c.commentId>0 order by c.commentId ASC ")
    public List<CommentForm> getCommentsOfPost(@Param("communityId") Long communityId);

}
