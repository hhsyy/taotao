package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	public EUDataGridResult getContentList(int page, int rows, long categoryId);

	public TaotaoResult insertContent(TbContent content);

}
