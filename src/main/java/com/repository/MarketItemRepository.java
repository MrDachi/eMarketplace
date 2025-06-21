package com.repository;

import com.entity.MarketItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketItemRepository extends JpaRepository<MarketItem, Long> {
}
