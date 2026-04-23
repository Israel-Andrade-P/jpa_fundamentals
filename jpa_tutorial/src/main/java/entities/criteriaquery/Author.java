package entities.criteriaquery;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@NamedEntityGraph(
        name = "Author.fetchBooks",
        attributeNodes = @NamedAttributeNode("books")
)
//that's how you use a named graph -> EntityGraph<?> graph = em.getEntityGraph("Author.fetchBooks");
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
/*
entity graphs, used to avoid n + 1 query problems. It loads all you need in one query
you could've used inner joins here as well, it solves the same problem
Author -> Book
EntityGraph<?> graph = em.createEntityGraph(Author.class);
graph.addAttributeNode("books");

em.createQuery("SELECT a FROM Author a", Author.class)
.setHint("jakarta.persistence.loadgraph", graph)
.getResultList()
.forEach(a -> IO.println(a.getBooks()));

you can go even deeper in the graph
//Author -> Book -> BookShop
EntityGraph<?> graph = em.createEntityGraph(Author.class);
SubGraph<?> bookSubGraph = (SubGraph<?>) graph.addSubgraph("books");
bookSubGraph.addAttributeNode("bookShops");

em.createQuery("SELECT a FROM Author a", Author.class)
       .setHint("jakarta.persistence.loadgraph", graph)
        .getResultList()
         .forEach(a ->
                   IO.println(
                            a.getBooks().stream().map(Book::getBookShops).toList()
                        )
                        );
 */
