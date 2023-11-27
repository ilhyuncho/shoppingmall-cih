package com.cih.shoppingmallcih.service.test;


import com.cih.shoppingmallcih.dto.test.product.ProductDTO;
import com.cih.shoppingmallcih.dto.test.product.ProductResponseDTO;


public interface ProductService {
    ProductResponseDTO getProduct(Long productID);
    ProductResponseDTO saveProduct(ProductDTO productDTO);
    ProductResponseDTO changeProductName(Long productID, String name) throws Exception;
    void deleteProduct(Long productID) throws Exception;

}
