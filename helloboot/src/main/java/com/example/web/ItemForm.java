package com.example.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 登録・更新画面のフォームの値をマッピングするフォームクラス
 */
@Data
public class ItemForm {
	@NotNull
	@Size(min = 1, max = 256)
	private String content;
}
