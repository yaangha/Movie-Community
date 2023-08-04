package perproject.moviecommunity.repository;

import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Reply;
import perproject.moviecommunity.domain.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemoryReplyRepository implements ReplyRepository {

    private static Map<Long, Reply> replyStore = new HashMap<>();
    private static long sequence = 0L;

    /**
     * 댓글 저장
     */
    @Override
    public Reply save(Reply reply) {
        reply.setId(++sequence);
        return replyStore.put(reply.getId(), reply);
    }

    /**
     * 대글 삭제
     */
    @Override
    public Reply remove(Reply reply) {
        return replyStore.remove(reply.getId());
    }

    /**
     * 리뷰 아이디에 맞는 댓글 조회시
     */
    @Override
    public List<Reply> findByReview(Review review) {
        return replyStore.values()
                .stream()
                .filter(reply -> reply.getReview().getId() == review.getId())
                .collect(Collectors.toList());
    }

    /**
     * 사용자가 작성한 댓글 조회시
     */
    @Override
    public List<Reply> findByMember(Member member) {
        return replyStore.values()
                .stream()
                .filter(reply -> reply.getMember().getId() == member.getId())
                .collect(Collectors.toList());
    }
}
