package service;

import entity.Customer;
import entity.ShoppingCard;
import repository.CustomerRepository;

import java.util.List;

public class CustomerService extends UserService<Customer, CustomerRepository> {

    private CustomerRepository customerRepository;

    public CustomerService() {
        super(new CustomerRepository());
        this.customerRepository = new CustomerRepository();
    }

    public void findShoppingCardByUserId(int id) {
        List<ShoppingCard> shoppingCardByUserId = customerRepository.findShoppingCardByUserId(id);
        for (ShoppingCard shoppingCard : shoppingCardByUserId) {
            System.out.println(shoppingCard.toString());
        }
    }

  //  @Override
  //  public void save(Customer customer) {
  //      super.save(customer);
  //  }
//
  //  @Override
  //  public void delete(int id) {
  //      super.delete(id);
  //  }
//
  //  @Override
  //  public void update(Customer customer) {
  //      super.update(customer);
  //  }
//
  //  @Override
  //  public Customer findById(int id) {
  //      return super.findById(id);
  //  }
//
  //  @Override
  //  public void findAll() {
  //      super.findAll();
  //  }
//
//
  //  @Override
  //  public Customer login(String username, String password) {
  //      return super.login(username, password);
  //  }
}
