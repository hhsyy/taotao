package com.taotao.rest.service;

import java.util.List;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	public List<TbContent> getContentListById(long contentCid);
}
