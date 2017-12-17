package com.taotao.protal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.util.TaotaoResult;
import com.taotao.protal.pojo.CartItem;

public interface CartService {

	TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

}
