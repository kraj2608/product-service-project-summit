package com.foodshop.productservice.respositories;

import com.foodshop.productservice.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoryRepository extends MongoRepository<Category,String> {
    boolean existsByName(String name);
    Category getCategoryById(String id);
    Category deleteCategoryById(String id);


}
