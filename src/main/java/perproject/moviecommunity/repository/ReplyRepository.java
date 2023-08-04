package perproject.moviecommunity.repository;

import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Reply;
import perproject.moviecommunity.domain.Review;

import java.util.List;

public interface ReplyRepository {

    Reply save(Reply reply);
    Reply remove(Reply reply);
    List<Reply> findByReview(Review review);
    List<Reply> findByMember(Member member);
}
