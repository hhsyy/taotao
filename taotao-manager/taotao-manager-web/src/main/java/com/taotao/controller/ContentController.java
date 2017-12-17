package com.taotao.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
public class ContentController {

	@Autowired
	public ContentService contentService;

	@RequestMapping("/content/query/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, long categoryId) {

		EUDataGridResult itemList = contentService.getContentList(page, rows, categoryId);

		return itemList;

	}

	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
	

}
