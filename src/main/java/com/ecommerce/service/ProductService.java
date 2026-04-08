package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDTO createProduct(ProductDTO dto){

        // Map incoming DTO to entity.
        Product product = modelMapper.map(dto, Product.class);

        if (dto.getCategoryId() != null) {
            // Attach category if provided.
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        }

        Product saved = productRepository.save(product);

        // Convert back to DTO for the response.
        return mapToDto(saved);
    }

    public Page<ProductDTO> getAllProducts(int page, int size){

        // Default sort by name for stable pagination.
        Pageable pageable =
                PageRequest.of(page, size, Sort.by("name"));

        Page<Product> products =
                productRepository.findAll(pageable);

        // Map each entity to DTO while keeping pagination metadata.
        return products.map(this::mapToDto);
    }

    public ProductDTO getProductById(Long id) {

        // Fail fast if the product does not exist.
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return mapToDto(product);
    }

    public List<ProductDTO> searchProducts(String keyword){

        // Case-insensitive name search.
        List<Product> products =
                productRepository.findByNameContainingIgnoreCase(keyword);

        return products.stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId){

        // Filter by category id at the repository level.
        List<Product> products =
                productRepository.findByCategoryId(categoryId);

        return products.stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<ProductDTO> filterByPrice(BigDecimal min, BigDecimal max){

        // Normalize null inputs to safe defaults.
        BigDecimal minValue = Optional.ofNullable(min).orElse(BigDecimal.ZERO);
        BigDecimal maxValue = Optional.ofNullable(max).orElse(new BigDecimal("1000000000"));

        List<Product> products = productRepository.findByPriceBetween(minValue, maxValue);

        return products.stream()
                .map(this::mapToDto)
                .toList();
    }

    public ProductDTO updateProduct(Long id, ProductDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Update mutable fields.
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());

        if (dto.getCategoryId() != null) {
            // Replace category if provided.
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        } else {
            // Allow clearing category when null is sent.
            product.setCategory(null);
        }

        return mapToDto(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        // Explicitly check existence to return a consistent not-found error.
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDto(Product product) {

        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        if (product.getCategory() != null) {
            // Preserve category id for API responses.
            dto.setCategoryId(product.getCategory().getId());
        }
        return dto;
    }
}
