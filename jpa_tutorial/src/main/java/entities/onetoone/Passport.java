package entities.onetoone;

import jakarta.persistence.*;

@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @OneToOne(mappedBy = "passport")//mappedBy represents the opposite side of the owner of the relationship
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
//        In a one to one, one-directional relationship we are able to get passport info through the person, but not the other way around
//        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE p.passport.number =:number", Person.class);
//        q.setParameter("number", "6969");
//        System.out.println(q.getResultList());
