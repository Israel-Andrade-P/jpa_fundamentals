package entities.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

//SINGLE_TABLE - one table is created for all children, and creates a column to discern the type called discriminator column
//var query = "select b from Book b";
//em.createQuery(query, Book.class).getResultList().forEach(IO::println); - jpa will do a WHERE in the discriminator to grab only book types
//alos your columns would have to be nullable which isn't a good design
//JOINED - creates three tables, gotta do joins in queries to grab all data
//TABLE_PER_CLASS - creates table per class not including the parent, theres column duplications among children, you gotta do unions which jpql doesn't support
//if you don't make your parent class a entity and use @MappedSuperClass, you can't query Product anymore
//jpql is polymorphic so if you query Product it knows you want to query it's children
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
