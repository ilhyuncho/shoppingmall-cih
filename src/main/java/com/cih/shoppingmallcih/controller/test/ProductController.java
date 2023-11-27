package com.cih.shoppingmallcih.controller.test;

import com.cih.shoppingmallcih.dto.test.product.ChangeProductNameDto;
import com.cih.shoppingmallcih.dto.test.product.ProductDTO;
import com.cih.shoppingmallcih.dto.test.product.ProductResponseDTO;
import com.cih.shoppingmallcih.service.test.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value="/{productId}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable(name="productId") Long productid) {

        ProductResponseDTO product = productService.getProduct(productid);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductDTO productDTO){
        ProductResponseDTO result = productService.saveProduct(productDTO);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDTO> changeProductName(@RequestBody ChangeProductNameDto data)
            throws Exception {
        ProductResponseDTO productResponseDTO = productService.changeProductName(data.getNumber(), data.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long ProductId) throws Exception{
        productService.deleteProduct(ProductId);
        
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다");
    }



}
