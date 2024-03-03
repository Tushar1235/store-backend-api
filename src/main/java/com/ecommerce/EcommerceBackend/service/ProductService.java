package com.ecommerce.EcommerceBackend.service;

import com.ecommerce.EcommerceBackend.Dto.ProductDto;
import com.ecommerce.EcommerceBackend.model.Category;
import com.ecommerce.EcommerceBackend.model.Product;
import com.ecommerce.EcommerceBackend.repository.CategoryRepository;
import com.ecommerce.EcommerceBackend.repository.ProductRepository;
import com.ecommerce.EcommerceBackend.view.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements Serializable {

    @Autowired
    ProductRepository productRepository;
    @Autowired
CacheManager cacheManager;
    @Autowired
    CategoryRepository categoryRepository;


    public void createProduct(ProductDto productDto) {
        Product product = toProduct(productDto);
        productRepository.save(product);
    }

    private Product toProduct(ProductDto productDto) {
        Product product = new Product();
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found"));
        product.setProduct_name(productDto.getProduct_name());
        product.setProductDescription(productDto.getProductDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setStock(product.isStock());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(category);
        return product;
    }

    @Cacheable(value = "products", key = "#root.methodName + #pageNumber + #pageSize + #sortBy + #sortDir")
    public ProductResponse getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = null;
        if (!Arrays.asList("product_id", "product_name", "productDescription", "price", "quantity", "stock", "imageUrl", "category").contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sortBy parameter");
        }
        if (sortDir.trim().equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Product> allProducts = productRepository.findAll(page);
        List<Product> products = allProducts.getContent();

        // Print the number of records retrieved
        System.out.println("Number of records retrieved: " + products.size());

        List<ProductDto> productDtoList = products.stream()
                .map(product -> toProductDto(product))
                .collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDtoList);
        productResponse.setPageNumber(allProducts.getNumber());
        productResponse.setPageSize(allProducts.getSize());
        productResponse.setTotalPages(allProducts.getTotalPages());
        productResponse.setLastPage(allProducts.isLast());

        return productResponse;
    }

    private ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProduct_id(product.getProduct_id());
        productDto.setProduct_name(product.getProduct_name());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setStock(product.isStock());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategoryId(product.getCategory().getCategoryId());
        return productDto;
    }

    @Cacheable(value = "productDetails", key = "#productId")
    public ProductDto getProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product Not found"));
        return toProductDto(product);
    }

    @Caching(
            put = { @CachePut(value = "productDetails", key = "#productId") },
            evict = {
                    @CacheEvict(value = "products", allEntries = true),
                    @CacheEvict(value = "productByCategory", key = "#productDto.categoryId"),
                    @CacheEvict(value = "productDetails", key = "#productId")
            }
    )
    public void updateProduct(int productId, ProductDto productDto){
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product Not found"));
        product.setProduct_name(productDto.getProduct_name());
        product.setProductDescription(productDto.getProductDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setStock(product.isStock());
        product.setImageUrl(productDto.getImageUrl());
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        cacheManager.getCache("productDetails").evict(productId);
    }


    @Caching(evict = {
            @CacheEvict(value = "productDetails", key = "#productId"),
            @CacheEvict(value = "products", allEntries = true),
            // If category information is stored in product details, consider evicting productByCategory cache as well
    })
    public void deleteProduct(int productId){
        productRepository.deleteById(productId);
        cacheManager.getCache("productDetails").evict(productId);
    }

    public List<ProductDto> getProductByCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException());
        List<Product> product = productRepository.findByCategory(category);
        List<ProductDto> productDtoList = product.stream().map(product1 -> toProductDto(product1)).collect(Collectors.toList());
        return  productDtoList;
    }

    @Cacheable(value = "productByCategory", key = "#categoryId")
    public void saveImage(String uploadImage, Product product) {
        product.setImageUrl(uploadImage);
        ProductDto dto = toProductDto(product);
        updateProduct(product.getProduct_id(),dto);
    }
}
