package com.taotao.protal.service;

import com.taotao.protal.pojo.ItemInfo;

public interface ItemService {

	ItemInfo getItemById(Long itemId);

	String getItemDescById(Long itemId);

	String getItemParam(Long itemId);

}
