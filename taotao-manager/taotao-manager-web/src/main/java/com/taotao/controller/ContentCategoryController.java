package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUNode;
import com.taotao.common.util.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@Controller
public class ContentCategoryController {

	@Autowired
	public ContentCategoryService categoryService;
	
	@RequestMapping("/content/category/list")
    @ResponseBody
	public List<EUNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<EUNode> list = categoryService.getContentCategoteById(parentId);
		
		System.out.println(list+":"+parentId);
		return list;
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId, String name) {
		TaotaoResult result = categoryService.insertContentCategory(parentId, name);
		return result;
	}
}
