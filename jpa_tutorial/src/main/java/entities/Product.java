package entities;

import entities.keys.ProductKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
@IdClass(ProductKey.class)
public class Product {
    @Id
    private String code;
    @Id
    private Long number;
    private String name;

    public Product(String code, Long number, String name) {
        this.code = code;
        this.number = number;
        this.name = name;
    }

    public Product() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
