package repository;

import entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements BaseRepository<Category> {
    private Connection connection = MyConnection.connection;

    @Override
    public Integer save(Category category) {
        String insert = "insert into category (title, description) VALUES (?,?);";
        Integer id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getTitle());
            preparedStatement.setString(2, category.getDescription());
          //  preparedStatement.setInt(3, category.getCategory().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to save category! ");
        }
        return  id;
    }

    @Override
    public void update(Category category) {
        String update = "update category set title = ? , description = ? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, category.getTitle());
            preparedStatement.setString(2, category.getDescription());
           // preparedStatement.setInt(3, category.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to update category ");
        }
    }

    @Override
    public List<Category> findAll() {
        String update = "select * from category; ";
        List<Category> categoryList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoryList.add(new Category(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to show category ");
        }
        return categoryList;
    }

    @Override
    public void delete(int id) {
        String delete = "delete from category where id = ?; ";
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
    public Category findById(int id) {
        Category category = null;
        String findById = "select * from category where id =? ; ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = new Category(resultSet.getString("title"),
                        resultSet.getString("description"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to delete category ");
        }
        return category;
    }
}
