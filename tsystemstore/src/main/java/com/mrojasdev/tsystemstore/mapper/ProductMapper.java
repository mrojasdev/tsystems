package com.mrojasdev.tsystemstore.mapper;

import com.mrojasdev.tsystemstore.model.Product;
import com.mrojasdev.tsystemstore.model.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO productToProductDTO(Product entity);

    Product productDTOtoProduct(ProductDTO dto);

    List<ProductDTO> productsToProductsDTO(List<Product> products);

    List<Product> productsDTOtoProducts(List<ProductDTO> dtos);

}
