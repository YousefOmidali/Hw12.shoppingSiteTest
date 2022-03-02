package repository;

import entity.ShoppingCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCardRepository implements BaseRepository<ShoppingCard> {
    private Connection connection = MyConnection.connection;

    @Override
    public Integer save(ShoppingCard shoppingCard) {
        String insert = "insert into shopping_card(date, payed) VALUES (?,?); ";
        Integer id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setDate(1, shoppingCard.getDate());
            preparedStatement.setBoolean(2, shoppingCard.isPayed());
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to save shopping card");
        }
        return id;
    }

    //
    @Override
    public void update(ShoppingCard shoppingCard) {
        String update = "update shopping_card set date = ? ,payed = ?  ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setDate(1, shoppingCard.getDate());
            preparedStatement.setBoolean(2, shoppingCard.isPayed());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to update shopping card ");
        }
    }

    @Override
    public List<ShoppingCard> findAll() {
        String findAll = "select * from shopping_card ; ";
        List<ShoppingCard> shoppingCardList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                shoppingCardList.add(new ShoppingCard(resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getBoolean("payed")));
            }
        } catch (SQLException e) {
            System.out.println("failed to show list of customer");
        }
        return shoppingCardList;
    }

    @Override
    public void delete(int id) {
        try {
            String delete = "delete from shopping_card where id = ? ; ";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Failed to delete shopping card");
        }
    }

    @Override
    public ShoppingCard findById(int id) {
        String findById = "select * from shopping_card where id = ? ;";
        ShoppingCard shoppingCard = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                shoppingCard = new ShoppingCard(resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getBoolean("payed"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find Shopping card");
        }
        return shoppingCard;
    }

}
