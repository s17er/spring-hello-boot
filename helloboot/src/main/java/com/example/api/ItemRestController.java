package com.example.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
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
	 * 全件取得のAPI（型はCSV）
	 * api/item で取得
	 * @return
	 */
	@RequestMapping(value = "csvfile", method = RequestMethod.GET)
	@ResponseBody
	void getItemsCsvFile(HttpServletResponse res) {
		res.setContentType("text/csv;charset=MS932");
		String fileName;
		try {
			fileName = new String("サンプル.csv".getBytes("MS932"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return;
		}
		// Headerを設定
		res.setHeader("Content-Disposition", "attachment; filename="+fileName);
		// 内容を出力
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.write(getItemsCsv());
			writer.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	/**
	 * 全件取得のAPI（型はCSV）
	 * api/item で取得
	 * @return
	 */
	@RequestMapping(value = "csv", method = RequestMethod.GET)
	@ResponseBody
	String getItemsCsv() {
		List<Item> items = this.itemService.findAll();
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(Item.class).withHeader().sortedBy("id", "content");
		try {
			String res = mapper.writer(schema).writeValueAsString(items);
			return res;
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return "error";
		}
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
