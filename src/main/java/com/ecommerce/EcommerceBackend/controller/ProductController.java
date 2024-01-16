package com.ecommerce.EcommerceBackend.controller;

import com.ecommerce.EcommerceBackend.Dto.ProductDto;
import com.ecommerce.EcommerceBackend.constants.AppConstants;
import com.ecommerce.EcommerceBackend.model.Product;
import com.ecommerce.EcommerceBackend.repository.ProductRepository;
import com.ecommerce.EcommerceBackend.service.FileUpload;
import com.ecommerce.EcommerceBackend.service.ProductService;
import com.ecommerce.EcommerceBackend.view.ApiResponse;
import com.ecommerce.EcommerceBackend.view.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    FileUpload fileUpload;
    @Autowired
    ProductRepository productRepository;
    @Value("${product.path.images}")
    private String imagePath;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
       productService.createProduct(productDto);
       return new ResponseEntity<>(new ApiResponse(true), HttpStatus.CREATED);
    }
    @PostMapping("/product/images/{productId}")
    public ResponseEntity<ApiResponse> uploadFile(@PathVariable int productId,
                                                  @RequestParam("product_image")MultipartFile file){
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product not found"));
        String imageName  = null;
        try{
            String uploadImage  = fileUpload.uploadFile(imagePath, file);
            productService.saveImage(uploadImage,product);
            return new ResponseEntity<>(new ApiResponse(true), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse(false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping
    public ResponseEntity<ProductResponse> getAllProducts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER_STRING,required = false) int pageNumber,
                                                          @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING,required = false) int pageSize,
                                                          @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_STRING,required = false) String sortBy,
                                                          @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING,required = false)String sortDir) {
        ProductResponse productResponse = productService.getAllProducts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

   @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable int productId){
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable int categoryId){
        return new ResponseEntity<>(productService.getProductByCategory(categoryId), HttpStatus.OK);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable int productId, @RequestBody ProductDto productDto){
        productService.updateProduct(productId,productDto);
        return new ResponseEntity<>(new ApiResponse(true), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new ApiResponse(true), HttpStatus.OK);
    }
}
