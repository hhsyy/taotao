package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageController {

	@Test
	public void testPage(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		
		TbItemExample example = new TbItemExample();
		
		PageHelper.startPage(1, 20);
		
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		for(TbItem item : list) {
			System.out.println(item.getTitle());
		}
		PageInfo<TbItem> page = new PageInfo<>(list);
		
		long total= page.getTotal();
		
		System.out.println(total);
		
	}
}
