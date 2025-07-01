package com.controller;


import ch.qos.logback.core.model.Model;
import com.dto.MarketItemRequest;
import com.entity.MarketItem;
import com.repository.MarketItemRepository;
import com.service.MarketItemService;
import jakarta.validation.Valid;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketItemService service;

    @Autowired
    public MarketController(MarketItemService service){
        this.service = service;
    }

    @GetMapping
    public Map<String, Object> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateDesc") String sort
    ) {
        Sort sorting;

        switch (sort) {
            case "dateAsc" -> sorting = Sort.by("submissionTime").ascending();
            case "priceAsc" -> sorting = Sort.by("price").ascending();
            case "priceDesc" -> sorting = Sort.by("price").descending();
            default -> sorting = Sort.by("submissionTime").descending();
        }

        Page<MarketItem> pageData = service.getAllItems(PageRequest.of(page, size, sorting));

        Map<String, Object> body = new HashMap<>();
        body.put("items", pageData.getContent());
        body.put("currentPage", pageData.getNumber());
        body.put("totalPages", pageData.getTotalPages());
        return body;
    }


    @PostMapping
    public ResponseEntity<MarketItem> create(@Valid @RequestBody MarketItemRequest request) {
        MarketItem saved = service.createItem(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/item.html?id=" + saved.getId())
                .body(saved);
    }

}
