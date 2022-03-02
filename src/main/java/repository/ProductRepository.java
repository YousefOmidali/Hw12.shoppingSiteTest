package repository;

import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements ProductInterface {
    private Connection connection = MyConnection.connection;


    @Override
    public Integer save(Product product) {
        String insert = "insert into product (name, description, category_id, qty, price) VALUES (?,?,?,?,?); ";
        Integer id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription() );
            preparedStatement.setInt(3, product.getCategory().getId());
            preparedStatement.setInt(4, product.getQty());
            preparedStatement.setInt(5, product.getPrice());
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to save product");
        }
        return id;
    }

    @Override
    public void update(Product product) {
        String update = "update product set description = ? , qty = ? , price = ? where  id = ? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);;
            preparedStatement.setString(1, product.getDescription() );
            preparedStatement.setInt(2, product.getQty());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4,product.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        String findAll = "select * from product;";
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                productList.add(new Product(resultSet.getInt("Id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getInt("qty"),
                        resultSet.getInt("category_id")));
            }
        } catch (SQLException e) {
            System.out.println("failed to show list of product");
        }
        return productList;
    }

    @Override
    public void delete(int id) {
        try {
            String delete = "delete from product where id = ? ; ";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Failed to delete product");
        }
    }

    @Override
    public Product findById(int id) {
        String findById = "select * from product where id = ? ;";
        Product product = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product(resultSet.getInt("Id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getInt("qty"),
                        resultSet.getInt("category_id"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find customer");
        }
        return product;
    }

    @Override
    public List<Product> findByCategory(int categoryId) {
        List<Product> productList = new ArrayList<>();
        Product product = null ;
        try {
            String findByCategory="select * from product inner join category c on c.id = product.category_id " +
                    "where product.id = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(findByCategory);
            preparedStatement.setInt(1,categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product =new Product(resultSet.getInt("Id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getInt("qty"),
                        resultSet.getInt("category_id"));
            }
        }catch (SQLException e){
            System.out.println("failed to find product by category id");
        }
        return productList;
    }
}
