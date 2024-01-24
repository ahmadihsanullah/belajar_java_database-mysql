package programmer.zaman.now.database.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import programmer.zaman.now.database.ConnectionUtil;
import programmer.zaman.now.database.entity.Customer;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void insert(Customer customer) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "INSERT INTO customers(name, email) VALUES (?,?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getEmail());
                preparedStatement.executeUpdate();
                
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if(resultSet.next()){
                        customer.setId(resultSet.getInt(1));
                    }
                }
            }
        }catch(SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Customer findById(Integer id) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "SELECT * FROM customers WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        return new Customer(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email")
                        );
                    }else{
                        return null;
                    }
                }
            }
        }catch(SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Customer> findAll() {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "SELECT * FROM customers";
            try(java.sql.Statement statement = connection.createStatement()){
                try(ResultSet resultset = statement.executeQuery(sql)){
                    List<Customer> customers = new ArrayList<>();
                    while (resultset.next()) {
                        customers.add(new Customer(
                            resultset.getInt("id"),
                            resultset.getString("name"),
                            resultset.getString("email")));
                    }
                    return customers;
                }
            }
        }catch(SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Customer> findAllByEmail(String email) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "SELECT * FROM customers WHERE email = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, email);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                        List<Customer> customers = new ArrayList<>();
                        while(resultSet.next()){
                            customers.add(new Customer(
                                resultSet.getInt("id"),
                                resultSet.getString("name"), 
                                resultSet.getString("email")));
                        }
                        return customers;
                }
            }
        }catch(SQLException exception){
            throw new RuntimeException(exception);
        }
    }
    
}
