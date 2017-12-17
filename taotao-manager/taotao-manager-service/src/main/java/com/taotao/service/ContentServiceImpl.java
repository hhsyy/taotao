package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	public TbContentMapper contentMapper;
	@Override
	public EUDataGridResult getContentList(int page, int rows,long  categoryId) {

		TbContentExample example = new TbContentExample();

		Criteria criteria = example.createCriteria();
		
		criteria.andCategoryIdEqualTo(categoryId);
		
		PageHelper.startPage(page, rows);

		List<TbContent> list = contentMapper.selectByExample(example);
		
		EUDataGridResult dataGridResult = new EUDataGridResult();

		dataGridResult.setRows(list);

		PageInfo<TbContent> pageInfo = new PageInfo<>(list);

		dataGridResult.setTotal(pageInfo.getTotal());

		return dataGridResult;
	}
	
	
	@Override
	public TaotaoResult insertContent(TbContent content) {
		
		Date date = new Date();
		//补全pojo内容
		content.setCreated(date);
		content.setUpdated(date);
		contentMapper.insert(content);
		
		return TaotaoResult.ok();
	}

	
}
