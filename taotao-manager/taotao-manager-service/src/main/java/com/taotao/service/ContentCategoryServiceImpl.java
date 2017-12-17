package com.taotao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUNode;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	public TbContentCategoryMapper categoryMapper;
	
	@Override
	public List<EUNode> getContentCategoteById(Long id) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		
		Criteria criteria = example.createCriteria();
		
		criteria.andParentIdEqualTo(id);
		
		List<TbContentCategory> list = categoryMapper.selectByExample(example);
		
		List<EUNode> nodes = new ArrayList<>();
		
		for (TbContentCategory c : list) {
			EUNode node = new EUNode();
			
			node.setId(c.getId());
			
			node.setText(c.getName());
			
			node.setState(c.getIsParent()?"closed" : "open");
			
			nodes.add(node);
		}
		return nodes;
	}
	
	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		
		Date date = new Date();
		//创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//'状态。可选值:1(正常),2(删除)',
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		//添加记录
		categoryMapper.insert(contentCategory);
		//查看父节点的isParent列是否为true，如果不是true改成true
		TbContentCategory parentCat = categoryMapper.selectByPrimaryKey(parentId);
		//判断是否为true
		if(!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			//更新父节点
			categoryMapper.updateByPrimaryKey(parentCat);
		}
		//返回结果
		return TaotaoResult.ok(contentCategory);
	}

}
