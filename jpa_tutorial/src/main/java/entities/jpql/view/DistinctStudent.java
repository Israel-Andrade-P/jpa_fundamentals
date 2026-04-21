package entities.jpql.view;

import jakarta.persistence.*;

@Entity
//represents a view in db that has complex native sql and we couldn't translate it to jpql
//so to stay in the jpql universe we created a view in db and then
@Table(name = "view_unique_students")
public class DistinctStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DistinctStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
