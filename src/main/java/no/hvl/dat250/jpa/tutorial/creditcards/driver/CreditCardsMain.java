package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.Address;
import no.hvl.dat250.jpa.tutorial.creditcards.Bank;
import no.hvl.dat250.jpa.tutorial.creditcards.CreditCard;
import no.hvl.dat250.jpa.tutorial.creditcards.Customer;
import no.hvl.dat250.jpa.tutorial.creditcards.Pincode;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
      try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
              PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
          em.getTransaction().begin();
          createObjects(em);
          em.getTransaction().commit();
      }
  }

  private static void createObjects(EntityManager em) {
    // Create bank
    Bank bank = new Bank();
    bank.setName("Pengebank");

    // Create shared Pincode
    Pincode pincode = new Pincode();
    pincode.setCode("123");
    pincode.setCount(1);

    // Create CreditCard 1
    CreditCard creditCard1 = new CreditCard();
    creditCard1.setNumber(12345);
    creditCard1.setBalance(-5000);
    creditCard1.setCreditLimit(-10000);
    creditCard1.setPincode(pincode);
    creditCard1.setOwningBank(bank);
    bank.getOwnedCards().add(creditCard1);
    // Create CreditCard 2
    CreditCard creditCard2 = new CreditCard();
    creditCard2.setNumber(123);
    creditCard2.setBalance(1);
    creditCard2.setCreditLimit(2000);
    creditCard2.setPincode(pincode);
    creditCard2.setOwningBank(bank);
    bank.getOwnedCards().add(creditCard2);
    // Create customer
    Customer customer = new Customer();
    customer.setName("Max Mustermann");

    // Set credit cards for customer
    customer.getCreditCards().add(creditCard1);
    customer.getCreditCards().add(creditCard2);
    creditCard1.setCustomer(customer);
    creditCard2.setCustomer(customer);

    // Create address
    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);

    // Set bidirectional link between customer and address
    customer.getAddresses().add(address);  // Add address to customer
    address.getOwners().add(customer);     // Add customer to address

    // Persist all entities
    em.persist(bank);
    em.persist(pincode);
    em.persist(address);
    em.persist(creditCard1);
    em.persist(creditCard2);
    em.persist(customer);
}

}
