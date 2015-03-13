package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Item;
import com.example.service.ItemService;

@RestController
@RequestMapping("api/items")
public class ItemRestController {
	
	@Autowired
	ItemService itemService;
	
	/**
	 * 全件取得のAPI
	 * api/item で取得
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	List<Item> getItems() {
		return this.itemService.findAll();
	}
	
	/**
	 * 新規作成API
	 * api/item でPOSTする
	 * {"item": "test data"}
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	Item postItem(@RequestBody Item item) {
		return this.itemService.create(item);
	}

	/**
	 * 更新API
	 * api/item/1 でPUTする
	 * 
	 * @param id
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	Item putItem(@PathVariable Integer id, @RequestBody Item item) {
		item.setId(id);
		return this.itemService.update(item);
	}

	/**
	 * 削除API
	 * api/item/1 でDELETEする
	 * 
	 * @param id
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteItem(@PathVariable Integer id) {
		this.itemService.delete(id);
	}

}
