package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;

@Entity
public class Pincode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer count;
    public void setCount(Integer count) {
        this.count = count;
    }

    private String code; 

    public void setCode(String code) {
        this.code = code;
    }

    @OneToOne(mappedBy = "pincode")
    private CreditCard creditCard;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Integer getCount() {
        return count;
    }
}
