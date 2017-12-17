package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemService;

@Controller
public class TbItemController {
	
	@Autowired
	public ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows){
		
		EUDataGridResult itemList = itemService.getItemList(page, rows);
		
		return itemList;
		
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item,String desc,String itemParams){
		
		TaotaoResult result = itemService.createItem(item,desc,itemParams);
		
		return result;
		
	}
	
	
	
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDataGridResult getItemParamList(Integer page, Integer rows){
		
		EUDataGridResult itemList = itemService.getItemParamList(page, rows);
		
		return itemList;
		
	}
}
