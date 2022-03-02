package service;

import entity.Admin;
import repository.AdminRepository;

public class AdminService extends UserService<Admin, AdminRepository> {
    public AdminService() {
        super(new AdminRepository());
    }

 //   @Override
 //   public void save(Admin admin) {
 //       super.save(admin);
 //   }
//
 //   @Override
 //   public void update(Admin admin) {
 //       super.update(admin);
 //   }
//
 //   @Override
 //   public void delete(int id) {
 //       super.delete(id);
 //   }
//
 //   @Override
 //   public void findAll() {
 //       super.findAll();
 //   }
//
 //   @Override
 //   public Admin findById(int id) {
 //       return super.findById(id);
 //   }
//
 //   @Override
 //   public Admin login(String username, String password) {
 //       return super.login(username, password);
 //   }
}
