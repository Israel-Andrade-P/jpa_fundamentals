package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
//    @GenericGenerator(name = "UUIDGenerator", type = UUIDGenerator.class)//custom Id generator, but this has been deprecated
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //IDENTITY - adds auto_increment to field Id
    @Column(length = 1000)
    private Long id;                               //TABLE - it creates a separate table that keeps track of the next id value(performance wise its not a good strategy)
    private String name;                             //UUID - it generates UUIDs for Id field instead of numbers
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
