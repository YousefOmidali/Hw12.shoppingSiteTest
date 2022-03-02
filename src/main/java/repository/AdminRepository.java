package repository;

import entity.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements UserInterface<Admin> {
    private Connection connection = MyConnection.connection;

    @Override
    public Integer save(Admin admin) {
        String save = "insert into Admin (username, password, national_code) VALUES (?,?,?);";
        Integer id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getNationalCode());
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to save admin");
        }
        return id;
    }

    @Override
    public void update(Admin admin) {
        try {
            String update = "update Admin set username = ? , password = ? , national_code = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getNationalCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("failed to update admin ");
        }

    }

    @Override
    public List<Admin> findAll() {
        String findAll = "select * from Admin;";
        List<Admin> adminList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            // List<String> list=new ArrayList<String>();
            if (resultSet.next()) {
                adminList.add(new Admin(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("national_code")));
                return adminList;
            }
        } catch (SQLException e) {
            System.out.println("failed to show list of admins");
        }
        return adminList;
    }

    @Override
    public void delete(int id) {
        try {
            String delete = "delete from Admin where id = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Failed to delete admin");
        }

    }

    @Override
    public Admin findById(int id) {
        String findById = "select * from admin where id=?;";
        Admin admin = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                        admin = new Admin(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("national_code"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete admin");
        }
        return admin;
    }

    @Override
    public Admin login(String username, String password) {
        String login = "select * from admin where username = ? and password = ? ; ";
        Admin admin = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(login);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("national_code"));
            }
        } catch (SQLException e) {
            System.out.println("failed to Login");
        }
        return admin;
    }
}
