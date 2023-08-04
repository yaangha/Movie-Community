package perproject.moviecommunity.service;

import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.domain.Reply;
import perproject.moviecommunity.domain.Review;
import perproject.moviecommunity.repository.MemoryReplyRepository;
import perproject.moviecommunity.repository.ReplyRepository;

import java.util.List;

public class ReplyService {

    private final ReplyRepository replyRepository = new MemoryReplyRepository();

    public Reply create(Reply reply) {
        return replyRepository.save(reply);
    }

    public Reply delete(Reply reply) {
        return replyRepository.remove(reply);
    }

    public List<Reply> readAllByReview(Review review) {
        return replyRepository.findByReview(review);
    }

    public List<Reply> readAllByMember(Member member) {
        return replyRepository.findByMember(member);
    }

}
