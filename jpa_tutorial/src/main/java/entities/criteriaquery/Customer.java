package entities.criteriaquery;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
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
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
//CRITERIA QUERIES:
/*CriteriaBuilder builder = em.getCriteriaBuilder();
CriteriaQuery<Customer> cq = builder.createQuery(Customer.class);

Root<Customer> customerRoot = cq.from(Customer.class);

cq.select(customerRoot); //SELECT c FROM Customer c
cq.select(customerRoot.get("name")); //SELECT c.name FROM Customer c;
cq.select(customerRoot).where(builder.ge(customerRoot.get("id"), 3)).orderBy(builder.desc(customerRoot.get("id")));
|-> SELECT c FROM Customer c WHERE c.id >= 3 ORDER BY c.id DESC

TypedQuery<Customer> query = em.createQuery(cq);

query.getResultList().forEach(IO::println);
*/