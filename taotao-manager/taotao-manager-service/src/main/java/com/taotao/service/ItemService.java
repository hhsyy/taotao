package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;

public interface ItemService {
	
	public TbItem getItemById(long id);
	
	public EUDataGridResult getItemList(int page, int rows);
	
	public TaotaoResult createItem(TbItem item,String desc,String itemParam);
	
	public EUDataGridResult getItemParamList(int page, int rows);
	
}
