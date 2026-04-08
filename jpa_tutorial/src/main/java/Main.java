import entities.Passport;
import entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;

void main() {
    String puName = "pu-name";
    Map<String, String> props = new HashMap<>();
    props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    props.put("hibernate.show_sql", "true");
    //never have this config in production, use a db versioning tool, never trust jpa
    props.put("hibernate.hbm2ddl.auto", "create");//create- drops if it exists and recreates tables | update- updates tables
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

        Person person = new Person();
        person.setName("Kyle");

        Passport passport = new Passport();
        passport.setNumber("6969");

        person.setPassport(passport);
        passport.setPerson(person);

        em.persist(person);
        em.persist(passport);

        //In a one to one, one-directional relationship we are able to get passport info through the person, but not the other way around
//        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE p.passport.number =:number", Person.class);
//        q.setParameter("number", "6969");
//        System.out.println(q.getResultList());

        em.getTransaction().commit();
    }
}