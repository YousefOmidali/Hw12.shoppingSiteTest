package repository;

import entity.Customer;
import entity.ShoppingCard;

import java.util.List;

public interface CustomerInterface extends UserInterface<Customer>{

    List<ShoppingCard> findShoppingCardByUserId(int id);
}
