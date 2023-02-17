package com.foodshop.productservice.respositories;

import com.foodshop.productservice.models.Category;
import com.foodshop.productservice.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends MongoRepository<Product,String> {
    Product getProductById(String id);

    List<Product> findAllByCategoriesContaining(List<Category> categories);

    List<Product> searchProductByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title,String product);

}
