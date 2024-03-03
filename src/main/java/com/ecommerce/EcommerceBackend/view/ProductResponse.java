package com.ecommerce.EcommerceBackend.view;

import com.ecommerce.EcommerceBackend.Dto.ProductDto;
import com.ecommerce.EcommerceBackend.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse implements Serializable {
    private List<ProductDto> content;
    private  int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean lastPage;
}
