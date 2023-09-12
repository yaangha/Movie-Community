package perproject.moviecommunity.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @Column
    private LocalDateTime clicked_time;

    public ReviewLike createReviewLike(Member member, Review review) {
        this.member = member;
        this.review = review;
        clicked_time = LocalDateTime.now();

        return this;
    }
}
