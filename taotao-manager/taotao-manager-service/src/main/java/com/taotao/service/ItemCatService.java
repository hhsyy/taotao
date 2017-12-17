package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUNode;

public interface ItemCatService {

	public List<EUNode> getItemCatList(long parent_id);
}
