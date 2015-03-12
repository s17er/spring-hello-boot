package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	public List<Item> findAll() {
		return this.itemRepository.findAll();
	}
	
	public Item findOne(Integer id) {
		return this.itemRepository.findOne(id);
	}
	
	public Item update(Item item) {
		return this.itemRepository.save(item);
	}
	
	/**
	 * 物理削除する（論理削除する場合は別途自分で実装すること。このサンプルでは論理削除は考慮していません）
	 * @param id
	 */
	public void delete(Integer id) {
		this.itemRepository.delete(id);
	}

}
