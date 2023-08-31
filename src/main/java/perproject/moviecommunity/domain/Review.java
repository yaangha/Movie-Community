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
//@SequenceGenerator(name = "REVIEW_SEQ_GEN", sequenceName = "REVIEW_SEQ", allocationSize = 1)
public class Review {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ_GEN")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    private String content;

    private LocalDateTime created_time;

    private LocalDateTime modified_time;

    /**
     * 0 = save, 1 = release, 2 = delete
     */
    private String status;
}
