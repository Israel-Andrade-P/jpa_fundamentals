package entities.jpql;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String model;
    private String color;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}

//SQL- SELECT * FROM cars; => get all columns from table cars
//JPQL- SELECT c FROM Car c; => get all attributes of Car entity from current persistence context
//String jpql = "select c from Car c";
//TypedQuery<Car> cars = em.createQuery(jpql, Car.class);
//"SELECT c FROM Car c WHERE c.price > :price AND p.name LIKE :name"; -> : represents a parameter, use .setParameter to set the values
//result.getResultList().forEach(IO::println)

//only select, update and delete operations, no inserts(entity manager .persist for inserts)

//FUNCTIONS:
//String jpql = "select AVG(c.price) from Car c";
//TypedQuery<Double> avg = em.createQuery(jpql, Double.class);
//IO.println(avg.getSingleResult());

//String jpql = "select COUNT(c) from Car c";
//TypedQuery<Long> count = em.createQuery(jpql, Long.class);
//IO.println(count.getSingleResult());

//String jpql = "select c from Car c where c.price = (select max(c2.price) from Car c2)";
//TypedQuery<Car> count = em.createQuery(jpql, Car.class);
//IO.println(count.getSingleResult());

//getting specific fields:
//String jpql = "select c.model, c.price from Car c";
//TypedQuery<Object[]> result = em.createQuery(jpql, Object[].class);
//result.getResultList().forEach(objects -> {
//    System.out.println(objects[0] + "-" + objects[1]);
//});
//String jpql = """
//                select c.model, avg(c.price)
//                from Car c
//                group by c.model
//                """;
//
//TypedQuery<Object[]> result = em.createQuery(jpql, Object[].class);
//
//        result.getResultList().forEach(objects -> {
//        System.out.println(objects[0] + "-" + objects[1]);
//        });