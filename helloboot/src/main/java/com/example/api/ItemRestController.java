package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Item;
import com.example.service.ItemService;

@RestController
@RequestMapping("api/items")
public class ItemRestController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(method = RequestMethod.GET)
	List<Item> getItems() {
		return this.itemService.findAll();
	}

}
