package programmer.zaman.now.database.repository;

import java.util.List;

import programmer.zaman.now.database.entity.Customer;

public interface CustomerRepository {
    void insert(Customer customer);

    Customer findById(Integer id);

    List<Customer> findAll();

    List<Customer> findAllByEmail(String email);
}
