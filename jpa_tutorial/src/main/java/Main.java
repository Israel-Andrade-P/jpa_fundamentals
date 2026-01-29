import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistence.CustomPersistenceUnitInfo;

void main() {

//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("my-persistence-unit");
    EntityManagerFactory factory = new HibernatePersistenceProvider()
            .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());
    EntityManager em = factory.createEntityManager();

    try {
        em.getTransaction().begin();

        Product p = new Product();

        p.setName("Chocolate");

        em.persist(p);// add it to the context -> NOT AN INSERT QUERY

        em.getTransaction().commit();
    } finally {
        em.close();
    }
}