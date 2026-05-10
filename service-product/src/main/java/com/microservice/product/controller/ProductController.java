package com.microservice.product.controller;

import com.microservice.common.response.ApiResponse;
import com.microservice.product.entity.Product;
import com.microservice.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ApiResponse<List<Product>> getAllProducts() {
        return ApiResponse.success("获取商品列表成功", productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> ApiResponse.success("获取商品信息成功", product))
                .orElse(ApiResponse.error(404, "商品不存在"));
    }

    @GetMapping("/category/{category}")
    public ApiResponse<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ApiResponse.success("获取分类商品列表成功", productRepository.findByCategory(category));
    }

    @PostMapping
    public ApiResponse<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ApiResponse.success("商品创建成功", savedProduct);
    }

    @PutMapping("/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setProductName(productDetails.getProductName());
                    product.setDescription(productDetails.getDescription());
                    product.setPrice(productDetails.getPrice());
                    product.setStock(productDetails.getStock());
                    product.setCategory(productDetails.getCategory());
                    return ApiResponse.success("商品更新成功", productRepository.save(product));
                })
                .orElse(ApiResponse.error(404, "商品不存在"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ApiResponse.success("商品删除成功", null);
        }
        return ApiResponse.error(404, "商品不存在");
    }
}
