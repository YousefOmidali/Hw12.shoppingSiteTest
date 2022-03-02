package repository;

import entity.Customer;
import entity.ShoppingCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements CustomerInterface {
    private Connection connection = MyConnection.connection;

    @Override
    public Integer save(Customer customer) {
        System.out.println(customer.toString());
        String insert = "insert into customer(username, password, address) VALUES (?,?,?); ";
        Integer id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to save customer");
        }
        return id;
    }

    @Override
    public void update(Customer customer) {
        try {
            String update = "update customer set username = ? ,password = ? ,address = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to update customer ");
        }
    }

    @Override
    public List<Customer> findAll() {
        String findAll = "select * from customer;";
        List<Customer> customerList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerList.add(new Customer(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("address")));
            }
        } catch (SQLException e) {
            System.out.println("failed to show list of customer");
        }
        return customerList;
    }

    @Override
    public void delete(int id) {
        try {
            String delete = "delete from customer where id = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Failed to delete customer");
        }
    }

    @Override
    public Customer findById(int id) {
        String findById = "select * from customer where id = ? ;";
        Customer customer = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("address"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find customer");
        }
        return customer;
    }

    @Override
    public List<ShoppingCard> findShoppingCardByUserId(int customerId) {
        String findShoppingCardByUserId = "select * from customer inner join order1 o on customer.id = o.customer_id \n" +
                "    inner join shopping_card sc on o.shopping_card_id = sc.id \n" +
                "    inner join product p on p.id = o.product_id where customer.id = ? ;";
        List<ShoppingCard> shoppingCardList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findShoppingCardByUserId);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shoppingCardList.add(new ShoppingCard(resultSet.getInt("Id"),
                        resultSet.getDate("date"),
                        resultSet.getBoolean("payed")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find customer");
        }
        return shoppingCardList;
    }

    @Override
    public Customer login(String username, String password) {
        String login = "select * from customer where username = ? and password = ? ; ";
        Customer customer = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(login);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("address"),
                        resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("failed to Login");
        }
        return customer;
    }
}
