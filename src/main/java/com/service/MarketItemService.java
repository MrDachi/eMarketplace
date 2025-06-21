package com.service;


import com.dto.MarketItemRequest;
import com.entity.MarketItem;
import com.repository.MarketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MarketItemService {

    private final MarketItemRepository repository;


    @Autowired
    public MarketItemService(MarketItemRepository repository) {
        this.repository = repository;
    }

    public Page<MarketItem> getAllItems(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public MarketItem getItem(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));
    }

    public MarketItem createItem(MarketItemRequest request) {
        MarketItem item = new MarketItem();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setDescription(request.getDescription());
        item.setPhotoUrl(request.getPhotoUrl());
        item.setSubmissionTime(LocalDateTime.now());
        return repository.save(item);
    }

    public Page<MarketItem> getSortedItems(String sortKey, boolean asc, Pageable pageable) {
        Sort sort = Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortKey);
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        return repository.findAll(sortedPageable);

    }
}















