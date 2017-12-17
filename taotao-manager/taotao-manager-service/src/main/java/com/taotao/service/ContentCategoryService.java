package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUNode;
import com.taotao.common.util.TaotaoResult;

public interface ContentCategoryService {
	
	public List<EUNode> getContentCategoteById(Long id);

	public TaotaoResult insertContentCategory(long parentId, String name);
}
