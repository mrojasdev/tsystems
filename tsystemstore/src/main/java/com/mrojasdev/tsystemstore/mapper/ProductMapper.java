package com.mrojasdev.tsystemstore.mapper;

import com.mrojasdev.tsystemstore.model.Product;
import com.mrojasdev.tsystemstore.model.ProductDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDTO productToProductDTO(Product entity);

    Product productDTOtoProduct(ProductDTO dto);

}
