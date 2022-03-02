package service;

import entity.ShoppingCard;
import repository.ShoppingCardRepository;

public class ShoppingCardService extends ShopService<ShoppingCard, ShoppingCardRepository> {

    public ShoppingCardService() {
        super(new ShoppingCardRepository());
    }

    public ShoppingCardService(ShoppingCardRepository shoppingCardRepository) {
        super(shoppingCardRepository);
    }

//    @Override
//    public void save(ShoppingCard shoppingCard) {
//        super.save(shoppingCard);
//    }
//
//    @Override
//    public void update(ShoppingCard shoppingCard) {
//        super.update(shoppingCard);
//    }
//
//    @Override
//    public void findAll() {
//        super.findAll();
//    }
//
//    @Override
//    public void delete(int id) {
//        super.delete(id);
//    }
//
//    @Override
//    public ShoppingCard findById(int id) {
//        return super.findById(id);
//    }
}
