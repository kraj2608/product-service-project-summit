package com.foodshop.productservice.respositories;

import com.foodshop.productservice.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends MongoRepository<Product,String> {
    Product getProductById(String id);
}
