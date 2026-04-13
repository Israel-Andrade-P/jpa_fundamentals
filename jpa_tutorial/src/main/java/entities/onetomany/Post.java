package entities.onetomany;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)//mappedBy always on the opposite side of the fk holder
    private Set<Comment> comments = new HashSet<>(); //if you declare the relationship only on the one to many side, jpa will treated as a many to many
                                   //and create a join table in db
                                   //if you don't establish the relationship on both sides the cascading won't work

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    //establishes the bidirectional relationship so stuff like cascading can work
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }
}
