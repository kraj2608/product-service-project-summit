package com.foodshop.productservice.respositories;

import com.foodshop.productservice.models.Category;
import com.foodshop.productservice.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends MongoRepository<Product,String> {
    Product getProductByIdAndDeleted(String id,boolean deleted);
    List<Product> findAllByDeleted(boolean deleted);

    @Query(value = "{ 'categories' : {'$all' : [?0] }}")
    List<Product> getProductsContainingCategories(String[] categories);

    List<Product> findAllByCategoriesContainingAndDeleted(List<Category> categories,boolean deleted);

    List<Product> searchProductByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndDeleted(String title,String product,boolean deleted);
}
