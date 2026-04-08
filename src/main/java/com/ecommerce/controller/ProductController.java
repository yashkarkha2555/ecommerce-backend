package com.ecommerce.controller;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @Valid @RequestBody ProductDTO dto){

        // Create a new product and return 201 on success.
        ProductDTO product = productService.createProduct(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        // Paginated product listing for catalog browsing.
        return ResponseEntity.ok(
                productService.getAllProducts(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id){

        // Fetch a single product by identifier.
        return ResponseEntity.ok(
                productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(
            @RequestParam String keyword){

        // Free-text search by product name.
        return ResponseEntity.ok(
                productService.searchProducts(keyword));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDTO>> getByCategory(
            @PathVariable Long id){

        // Filter products by category id.
        return ResponseEntity.ok(
                productService.getProductsByCategory(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTO>> filterByPrice(
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max){

        // Filter products by a price range.
        return ResponseEntity.ok(
                productService.filterByPrice(min, max));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO dto){

        // Update core product fields and optional category.
        return ResponseEntity.ok(
                productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        // Remove product and return 204 when successful.
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
