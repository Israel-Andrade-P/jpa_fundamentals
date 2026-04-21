import entities.criteriaquery.Author;
import entities.criteriaquery.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;

void main() {
    String puName = "pu-name";
    Map<String, String> props = new HashMap<>();
    props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    props.put("hibernate.show_sql", "true");
    //never have this config in production, use a db versioning tool, never trust jpa
    props.put("hibernate.hbm2ddl.auto", "none");//create- drops if it exists and recreates tables | update- updates tables
//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("my-persistence-unit");
    EntityManagerFactory factory = new HibernatePersistenceProvider()
            .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);
    //The Persistence Context is created when the EntityManager is created

    try (EntityManager em = factory.createEntityManager()) {
        em.getTransaction().begin();

//        em.persist(); -> adds entity to context, transitions from transient to managed, and marks it to persist, schedules an INSERT
//        em.remove();  -> marks managed entity for deletion
//        em.find();    -> gets by PK from DB, ands adds it to the context (first it looks for the entity in context, if it isn't there it goes grab it from db)
//        em.merge();   -> copies state into a managed entity and returns it, adds to the context
//        em.refresh(); -> reloads entity state FROM DB into context, refresh mirrors the current db state to context, resetting all changes in context
//        em.detach();  -> detaches it from the context, changes are no longer tracked, no SQL will be generated for it
//        em.getReference(); -> it gets a shell of the entity, no queries are sent to db, unless you do something with it

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Author> mainQuery = builder.createQuery(Author.class);
        Root<Author> authorRoot = mainQuery.from(Author.class);

        /*
        SELECT a, (SELECT COUNT(b) FROM Book b JOIN Author a ON b.id IN a.books) n FROM Author a WHERE n > 2
         */

        Subquery<Long> subquery = mainQuery.subquery(Long.class);
        Root<Author> subRootAuthor = subquery.correlate(authorRoot);
        Join<Author, Book> authorBookJoin = subRootAuthor.join("books");

        subquery.select(builder.count(authorBookJoin));
        mainQuery.select(authorRoot).where(builder.greaterThan(subquery, 2L));

        TypedQuery<Author> q = em.createQuery(mainQuery);

        q.getResultList().forEach(IO::println);

        em.getTransaction().commit();
    }
}