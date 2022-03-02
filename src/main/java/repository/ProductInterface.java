package repository;

import entity.Product;

import java.util.List;

public interface ProductInterface extends BaseRepository<Product> {

    List<Product> findByCategory(int categoryId);
}
