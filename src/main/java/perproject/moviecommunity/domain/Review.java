package perproject.moviecommunity.domain;

import java.time.LocalDateTime;

public class Review {

    private Long id;
    private Member member;
    private String title;
    private String content;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    private String status;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", member=" + member +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created_time=" + created_time +
                ", modified_time=" + modified_time +
                ", status='" + status + '\'' +
                '}';
    }
}
