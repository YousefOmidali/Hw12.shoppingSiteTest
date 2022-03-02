package repository;

import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements BaseRepository<Order> {
    private Connection connection = MyConnection.connection;

    @Override
    public Integer save(Order order) {
        String insert = "insert into order1 (product_id, customer_id) VALUES (?,?); ";
        Integer id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getProduct().getId());
            preparedStatement.setInt(2, order.getCustomer().getId());
           // preparedStatement.setInt(3,order.getShoppingCard().getId() );
            preparedStatement.execute();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }
            preparedStatement.close();
        } catch (
                SQLException e) {
            e.printStackTrace(); //System.out.println("failed to save order");
        }
        return  id;
    }

    @Override
    public void update(Order order) {
        String update = "update order1 set product_id=?,customer_id=?,shopping_card_id=? " +
                "where product_id = ? and customer_id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, order.getProduct().getId());
            preparedStatement.setInt(2, order.getCustomer().getId());
            preparedStatement.setInt(3,order.getShoppingCard().getId() );
            preparedStatement.setInt(4,order.getProduct().getId() );
            preparedStatement.setInt(5,order.getCustomer().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to update category ");
        }
    }

    @Override
    public List<Order> findAll() {
        String findAll = "select * from order1 ;";
        List<Order> orderList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderList.add(new Order(resultSet.getInt("product_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("shopping_card_id")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to show orderList ");
        }
        return orderList;
    }

    @Override
    public void delete(int id) {
        String delete = "delete from order1 where id = ?; ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to delete category ");
        }
    }

    @Override
    public Order findById(int id) {
        String findById = "select * from order1 where id = ? ;";
        Order order = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order(resultSet.getInt("product_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("shopping_card_id"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find order");
        }
        return order;
    }
}
