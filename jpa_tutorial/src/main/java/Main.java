import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;

void main() {
    String puName = "pu-name";
    Map<?, ?> props = new HashMap<>();

//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("my-persistence-unit");
    EntityManagerFactory factory = new HibernatePersistenceProvider()
            .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);
    //The Persistence Context is created when the EntityManager is created
    EntityManager em = factory.createEntityManager();

    try {
        em.getTransaction().begin();

//        em.persist(); -> adds entity to context, transitions from transient to managed, and marks it to persist, schedules an INSERT
//        em.remove();  -> marks managed entity for deletion
//        em.find();    -> gets by PK from DB, ands adds it to the context
//        em.merge();   -> copies state into a managed entity and returns it, adds to the context
//        em.refresh(); -> reloads entity state FROM DB into context
//        em.detach();  -> detaches it from the context, changes are no longer tracked, no SQL will be generated for it

       var e1 = em.find(Employee.class, 1);
       e1.setName("Zel Andrade");

        System.out.println(e1);

        em.getTransaction().commit();
    } finally {
        em.close();
    }
}