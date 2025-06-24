package com.controller;


import org.springframework.ui.Model;
import com.entity.MarketItem;
import com.repository.MarketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    @Autowired
    private MarketItemRepository marketItemRepository;

    @GetMapping("/items/{id}")
    public String itemDetail (@PathVariable Long id, Model model){
        MarketItem item = marketItemRepository.findById(id).orElse(null);
        if (item == null){
            return "redirect:/items";
        }
        model.addAttribute("item", item);
        return "item";
    }
}
