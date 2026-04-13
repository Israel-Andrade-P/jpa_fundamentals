package entities.onetomany;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "post_id")//@JoinColumn always on the fk holder side
    private Post post;//There's three options, one directional from Post to Comment or the other way around or a bidirectional relationship
                      //where both entities are aware of each other
                      //in a bidirectional case always establish the in memory relationship between the two as well


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
