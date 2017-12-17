package com.taotao.service;

import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

	public TaotaoResult getItemParamById(long id);

	public TaotaoResult insertItemParam(TbItemParam itemParam);

	public TaotaoResult insertItemParamItem(Long itemId, String itemParam);
}
