package com.example.web;

import java.util.List;

import io.undertow.attribute.RequestMethodAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Item;
import com.example.service.ItemService;

/**
 * ToDoリストに関するコントローラ
 */
@Controller
@RequestMapping("items")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		// DBからエンティティを取得する
		List<Item> items = this.itemService.findAll();
		// テンプレート内で参照するエンティティを設定する
		model.addAttribute("items", items);
		// テンプレートのファイルパスを返す
		return "items/list";
	}
}
