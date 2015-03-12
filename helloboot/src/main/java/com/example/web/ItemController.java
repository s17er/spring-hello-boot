package com.example.web;

import java.util.List;

import io.undertow.attribute.RequestMethodAttribute;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	/**
	 * 画面のフォーム値とマッピングするフォームクラスの初期化
	 * RequestMappingアノテーションがついているメソッドがコールされる前に実行され、
	 * このメソッドが呼ばれた結果が、Model#addAttribute()により、モデルクラスにセットされる
	 * @return
	 */
	@ModelAttribute
	ItemForm setUpForm() {
		return new ItemForm();
	}
	
	/**
	 * ToDo一覧画面を返す
	 * @param model ブラウザに返す画面で参照するエンティティを受け渡すオブジェクト
	 * @return 一覧画面のテンプレートパス
	 */
	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		// DBからエンティティを取得する
		List<Item> items = this.itemService.findAll();
		// テンプレート内で参照するエンティティを設定する
		model.addAttribute("items", items);
		// テンプレートのファイルパスを返す
		return "items/list";
	}
	
	/**
	 * 登録画面を返す
	 * 
	 * @param form フォームクラス
	 * @return 登録画面のテンプレートパス
	 */
	@RequestMapping(value = "add", params = "form",  method = RequestMethod.GET) 
	String addForm(ItemForm form) {
		return "items/add";
	}

	/**
	 * 登録処理
	 * 
	 * @param form 画面のフォームに入力された値がマッピングされたフォームクラス
	 * @param result フォームのバリデーションを実行した結果が格納される（第一引数に@Validatedアノテーションが付いている場合）
	 * @return 登録画面のテンプレートパス
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST) 
	String add(@Validated ItemForm form, BindingResult result) {
		// バリデーションエラーの場合は追加画面に戻る（フォームクラスを渡すことにより、入力値は保たれる）
		if(result.hasErrors()) {
			return addForm(form);
		}
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		this.itemService.create(item);
		return "redirect:/items";
	}

	/**
	 * 編集画面を返す
	 * 
	 * @param id hiddenフィールドに設定されたID
	 * @param form 画面のフォームに入力された値がマッピングされたフォームクラス
	 * @return 登録画面のテンプレートパス
	 */
	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET) 
	String editForm(@RequestParam Integer id, ItemForm form) {
		Item item = this.itemService.findOne(id);
		BeanUtils.copyProperties(item, form);
		return "items/edit";
	}

	/**
	 * 登録処理
	 * 
	 * @param form 画面のフォームに入力された値がマッピングされたフォームクラス
	 * @param result フォームのバリデーションを実行した結果が格納される（第一引数に@Validatedアノテーションが付いている場合）
	 * @return 登録画面のテンプレートパス
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST) 
	String edit(@RequestParam Integer id, @Validated ItemForm form, BindingResult result) {
		// バリデーションエラーの場合は編集画面に戻る（フォームクラスを渡すことにより、入力値は保たれる）
		if(result.hasErrors()) {
			return editForm(id, form);
		}
		Item item = this.itemService.findOne(id);
		BeanUtils.copyProperties(form, item);
		this.itemService.update(item);
		return "redirect:/items";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST) 
	String delete(@RequestParam Integer id) {
		this.itemService.delete(id);
		return "redirect:/items";
	}



}
