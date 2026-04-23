package entities.criteriaquery;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "books_book_shops",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "book_shop_id")
    )
    private Set<BookShop> bookShops = new HashSet<>();

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

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<BookShop> getBookShops() {
        return bookShops;
    }

    public void setBookShops(Set<BookShop> bookShops) {
        this.bookShops = bookShops;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
/*
joins with criteria query:
CriteriaBuilder builder = em.getCriteriaBuilder();
CriteriaQuery<Tuple> cq = builder.createTupleQuery();
Root<Book> bookRoot = cq.from(Book.class);
Join<Book, Author> authorJoin =  bookRoot.join("authors", JoinType.LEFT);
cq.multiselect(bookRoot, authorJoin);//SELECT b, a FROM Book b LEFT JOIN Author a
TypedQuery<Tuple> result = em.createQuery(cq);
result.getResultList().forEach(t -> System.out.println(t.get(0) + " : " + t.get(1)));

lots of joins:
Root<Book> bookRoot = cq.from(Book.class);
Join<Book, Author> authorJoin =  bookRoot.join("authors", JoinType.LEFT);
Join<Book, BookShop> shopJoin =  bookRoot.join("bookShops", JoinType.LEFT);
cq.multiselect(bookRoot, authorJoin, shopJoin);//SELECT b, a FROM Book b LEFT JOIN Author a

subqueries:
CriteriaBuilder builder = em.getCriteriaBuilder();
CriteriaQuery<Author> mainQuery = builder.createQuery(Author.class);
Root<Author> authorRoot = mainQuery.from(Author.class);

SELECT a, (SELECT COUNT(b) FROM Book b JOIN Author a ON b.id IN a.books) n FROM Author a WHERE n > 2

Subquery<Long> subquery = mainQuery.subquery(Long.class);
Root<Author> subRootAuthor = subquery.correlate(authorRoot);
Join<Author, Book> authorBookJoin = subRootAuthor.join("books");

subquery.select(builder.count(authorBookJoin));
mainQuery.select(authorRoot).where(builder.greaterThan(subquery, 2L));

TypedQuery<Author> q = em.createQuery(mainQuery);

q.getResultList().forEach(IO::println);

select a1_0.id,a1_0.name from authors a1_0 where (select count(b1_0.book_id) from books_authors b1_0 where a1_0.id=b1_0.author_id)>?
*/