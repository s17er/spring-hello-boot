package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("client")
public class ClientController {

	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		// テンプレートのファイルパスを返す
		return "client/index";
	}
}
