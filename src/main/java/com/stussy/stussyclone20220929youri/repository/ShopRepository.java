package com.stussy.stussyclone20220929youri.repository;

import com.stussy.stussyclone20220929youri.domain.CollectionProduct;
import com.stussy.stussyclone20220929youri.domain.Product;
import com.stussy.stussyclone20220929youri.domain.ProductDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopRepository {
    public List<CollectionProduct> getCollectionList(Map<String, Object> map) throws Exception;
    public List<ProductDetail> getProduct(int groupId) throws Exception;
}
