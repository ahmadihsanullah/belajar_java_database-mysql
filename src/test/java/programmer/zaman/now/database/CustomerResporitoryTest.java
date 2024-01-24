package programmer.zaman.now.database;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programmer.zaman.now.database.entity.Comment;
import programmer.zaman.now.database.entity.Customer;
import programmer.zaman.now.database.repository.CustomerRepository;
import programmer.zaman.now.database.repository.CustomerRepositoryImpl;

public class CustomerResporitoryTest {
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
        customerRepository = new CustomerRepositoryImpl();
    }

    @Test
    void testInsert(){
        Customer customer = new Customer( "ahmad ihsanullah", "ahmadihsan@gmail.com");
        customerRepository.insert(customer);

        assertNotNull(customer.getId());
    }

    @Test
    void testFindById(){
        Customer customer = customerRepository.findById(8);
        Assertions.assertNotNull(customer);
        System.out.println(customer.getId());
        System.out.println(customer.getName());
        System.out.println(customer.getEmail());

        Customer customerNotFound = customerRepository.findById(00000);
        assertNull(customerNotFound);
    }

     @Test
    void testFindAll() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers.size());
    }

    @Test
    void testFindByEmail() {
        List<Customer> customers = customerRepository.findAllByEmail("ahmadihsan@gmail.com");
        System.out.println(customers.size());
    }
    
    

}
