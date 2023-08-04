package perproject.moviecommunity.domain;

import java.time.LocalDateTime;

public class Review {

    private Long id;
    private Member member;
    private String content;
    private LocalDateTime created_time;
    private LocalDateTime modified_tile;

    // private int movie_id;

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

    public LocalDateTime getModified_tile() {
        return modified_tile;
    }

    public void setModified_tile(LocalDateTime modified_tile) {
        this.modified_tile = modified_tile;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", member=" + member +
                ", content='" + content + '\'' +
                ", created_time=" + created_time +
                ", modified_tile=" + modified_tile +
                '}';
    }
}
