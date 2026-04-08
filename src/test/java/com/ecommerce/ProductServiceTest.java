package com.ecommerce;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void filterByPriceReturnsMatchingProducts() {
        Category category = new Category();
        category.setName("Electronics");
        category = categoryRepository.save(category);

        ProductDTO product = new ProductDTO();
        product.setName("Phone");
        product.setDescription("Smartphone");
        product.setPrice(new BigDecimal("499.99"));
        product.setStockQuantity(10);
        product.setCategoryId(category.getId());

        productService.createProduct(product);

        List<ProductDTO> results = productService.filterByPrice(
                new BigDecimal("100.00"), new BigDecimal("600.00"));

        assertFalse(results.isEmpty());
        assertEquals("Phone", results.get(0).getName());
    }
}
