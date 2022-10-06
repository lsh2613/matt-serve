package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.Comment;
import mat.mat_t.domain.Community;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.CommentForm;
import mat.mat_t.form.CommentReDto;
import mat.mat_t.web.repository.CommentRepository;
import mat.mat_t.web.repository.CommunityRepository;
import mat.mat_t.web.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    /* 생성 */
    @Transactional
    public void createComments(String loginId, Long communityId, CommentReDto dto, String content) {
        User user = userRepository.findByWriter(loginId);
        Community community = communityRepository.findById(communityId).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + communityId));
        dto.setUser(user);
        dto.setCommunity(community);
        dto.setContent(content);
        Comment comment = dto.toEntity();
        commentRepository.save(comment);
    }

    /* 해당 커뮤 댓글 전체 조회 */
    @Transactional(readOnly = true)
    public List<CommentForm> Listcomments(long communityId) {
        return commentRepository.getCommentsOfPost(communityId);
    }

    /* 한개 조회 */
    @Transactional(readOnly = true)
    public CommentForm readComment(long commentId) {
        return commentRepository.getCommentByCommentId(commentId);
    }

    /* 수정 */
    @Transactional
    public void updateComments(Long communityId, CommentReDto dto) {
        Comment comment = commentRepository.findById(communityId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + communityId));
        comment.update(dto.getContent());
    }

    /* 삭제 */
    @Transactional
    public void deleteComments(Long communityId) {
        Comment comment = commentRepository.findById(communityId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + communityId));
        commentRepository.delete(comment);
    }


}
