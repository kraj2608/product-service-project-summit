package com.foodshop.productservice.respositories;

import com.foodshop.productservice.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends MongoRepository<Product,String> {
    Product getProductByIdAndDeleted(String id,boolean deleted);
    List<Product> findAllByDeleted(boolean deleted);

}
