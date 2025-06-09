package com.repository;

import com.entity.MarketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketItemRepository extends JpaRepository<MarketItem, Long> {
}
