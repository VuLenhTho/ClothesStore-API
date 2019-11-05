package com.vulenhtho.clothesboot.service.impl;

import com.vulenhtho.clothesboot.entity.Product;
import com.vulenhtho.clothesboot.mapper.ProductMapper;
import com.vulenhtho.clothesboot.model.request.IdsRequest;
import com.vulenhtho.clothesboot.model.request.ProductAdminRequest;
import com.vulenhtho.clothesboot.model.request.ProductRequest;
import com.vulenhtho.clothesboot.model.respone.ProductFilterResponse;
import com.vulenhtho.clothesboot.model.respone.ProductResponse;
import com.vulenhtho.clothesboot.model.respone.ProductWebResponse;
import com.vulenhtho.clothesboot.repository.ProductRepository;
import com.vulenhtho.clothesboot.service.ProductService;
import com.vulenhtho.clothesboot.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void insert(ProductRequest productRequest) {
        Date date = new Date();
        Product product = productMapper.transferToProduct(productRequest, new Product());
        product.setCreatedBy(productRequest.getCreator());
        product.setCreatedDate(new Timestamp(date.getTime()));

        productRepository.save(product);
    }

    @Override
    public void update(Long id, ProductRequest productRequest) {
        Date date = new Date();
        Product productExist = productRepository.getOne(id);
        Product newProduct = productMapper.transferToProduct(productRequest, productExist);

        newProduct.setModifiedBy(productRequest.getCreator());
        newProduct.setModifiedDate(new Timestamp(date.getTime()));

        productRepository.save(newProduct);

    }

    @Override
    public void delete(Long id) {
        productRepository.delete(productRepository.getOne(id));
    }

    @Override
    public void delete(IdsRequest ids) {
        for (Long id : ids.getIds()) {
            delete(id);
        }
    }

    @Override
    public List<ProductResponse> findAll() {
        return productMapper.transferToProductsResponse(productRepository.findAll());
    }

    @Override
    public ProductFilterResponse findAllWithFilter(ProductAdminRequest filterRequest) {
        ProductFilterResponse productFilterResponse = new ProductFilterResponse();

        List<Product> products = productRepository.findAll(
                ProductSpecification.filterProduct(filterRequest)
                , PageRequest.of(
                        filterRequest.getPage()
                        , filterRequest.getSize()
                        , sort(filterRequest.getSort()))).getContent();
        Long countAllProduct = productRepository.count(ProductSpecification.filterProduct(filterRequest));
        int total = (int) Math.ceil((double) countAllProduct / filterRequest.getSize());
        productFilterResponse.setCurrentPage(filterRequest.getPage());
        productFilterResponse.setTotalPages(total);
        productFilterResponse.setProducts(productMapper.transferToProductsResponse(products));
        return productFilterResponse;

    }

    private Sort sort(String typeSort) {
        if (typeSort!= null){
            switch (typeSort) {
                case "hot-des":
                    return Sort.by("hot").descending();
                case "hot-asc":
                    return Sort.by("hot").ascending();
                case "date-asc":
                    return Sort.by("createdDate").ascending();
                case "date-des":
                    return Sort.by("createdDate").descending();
                case "price-asc":
                    return Sort.by("price").ascending();
                case "price-des":
                    return Sort.by("price").descending();
            }
        }
        return Sort.by("createdDate").descending();

    }

    @Override
    public ProductResponse findById(Long id) {
        return null;
    }

    @Override
    public ProductWebResponse findByIdWeb(Long id) {
        return productMapper.transferToProductWebResponse(productRepository.getOne(id));
    }
}
