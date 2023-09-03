package perproject.moviecommunity.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
//@Entity
public class Review {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
    private Member member;

//    @Column(nullable = false)
    private String title;

    private String content;

    private LocalDateTime created_time;

    private LocalDateTime modified_time;

    /**
     * 0 = save, 1 = release, 2 = delete
     */
    private String status;
}
