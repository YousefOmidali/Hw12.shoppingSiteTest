package service;

import entity.Product;
import repository.ProductRepository;

import java.util.List;

public class ProductService extends ShopService<Product, ProductRepository> {

    private ProductRepository productRepository;

    public ProductService() {
        super(new ProductRepository());
        productRepository=new ProductRepository();
    }

    public void findByCategory(int id) {
            List<Product> byCategory = productRepository.findByCategory(id);
            for (Product product : byCategory) {
                System.out.println(product.toString());
            }
    }

    public ProductService(ProductRepository productRepository) {
        super(productRepository);
    }

//    @Override
//    public void save(Product product) {
//        super.save(product);
//    }
//
//    @Override
//    public void update(Product product) {
//        super.update(product);
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
//    public Product findById(int id) {
//        return super.findById(id);
//    }
}
