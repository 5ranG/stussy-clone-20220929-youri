package com.stussy.stussyclone20220929youri.service;

import com.stussy.stussyclone20220929youri.dto.shop.CollectionListRespDto;
import com.stussy.stussyclone20220929youri.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    @Override
    public List<CollectionListRespDto> getCollections(String category, int page) throws Exception {
        List<CollectionListRespDto> response = new ArrayList<CollectionListRespDto>();


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("category", category);
        map.put("index", (page - 1) * 16);

        shopRepository.getCollectionList(map).forEach(collection -> {
            response.add(collection.toListRespDto());
        });

        return response;
    }
}
