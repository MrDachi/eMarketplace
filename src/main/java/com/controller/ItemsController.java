package com.controller;

import com.entity.MarketItem;
import com.repository.MarketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ItemsController {
    @PostMapping("/items")
    public String addItem(@ModelAttribute MarketItem item){
        marketItemRepository.save(item);
        return "redirect:/items";
    }

    @Autowired
    private MarketItemRepository marketItemRepository;

    @GetMapping("/items")
    public String items(Model model){
        List<MarketItem> items = marketItemRepository.findAll();
        model.addAttribute("items", items);
        return "items";
    }
}
