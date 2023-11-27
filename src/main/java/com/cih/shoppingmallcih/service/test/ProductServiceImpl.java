package com.cih.shoppingmallcih.service.test;

import com.cih.shoppingmallcih.domain.test.product.Product;
import com.cih.shoppingmallcih.domain.test.product.ProductRepository;
import com.cih.shoppingmallcih.dto.test.product.ProductDTO;
import com.cih.shoppingmallcih.dto.test.product.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public ProductResponseDTO getProduct(Long productID){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        Optional<Product> result = productRepository.findById(productID);

        if(result.isPresent()){
            Product product = result.get();

            productResponseDTO.setNumber(product.getNumber());
            productResponseDTO.setName(product.getName());
            productResponseDTO.setPrice(product.getPrice());
            productResponseDTO.setStock(product.getStock());
        }
        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO saveProduct(ProductDTO productDTO) {

        Product product = modelMapper.map(productDTO, Product.class);

        Product result = productRepository.save(product);

        ProductResponseDTO dtoData = modelMapper.map(result, ProductResponseDTO.class);

        return dtoData;
    }

    @Override
    public ProductResponseDTO changeProductName(Long productID, String name) throws Exception {

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        Optional<Product> findProduct = productRepository.findById(productID);
        if(findProduct.isPresent()){
            Product product = findProduct.get();
            product.setName(name);

            Product save = productRepository.save(product);

            productResponseDTO = modelMapper.map(save, ProductResponseDTO.class);
        }
        else{
            throw new Exception();
        }
        return productResponseDTO;
    }

    @Override
    public void deleteProduct(Long productID) throws Exception {

        Optional<Product> findProduct = productRepository.findById(productID);
        if(findProduct.isPresent()) {
            Product product = findProduct.get();

            productRepository.delete(product);
        }
        else{
            throw new Exception();
        }
    }

}
