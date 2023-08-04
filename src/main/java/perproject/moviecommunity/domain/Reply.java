package perproject.moviecommunity.domain;

import java.time.LocalDateTime;

public class Reply {

    private Long id;
    private Member member;
    private Review review;
    private String content;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated_time() {
        return created_time;
    }

    public void setCreated_time(LocalDateTime created_time) {
        this.created_time = created_time;
    }

    public LocalDateTime getModified_time() {
        return modified_time;
    }

    public void setModified_time(LocalDateTime modified_time) {
        this.modified_time = modified_time;
    }
}
