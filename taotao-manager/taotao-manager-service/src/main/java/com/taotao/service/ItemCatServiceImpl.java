package com.taotao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;
import com.taotao.common.pojo.EUNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper catMapper;
	
	@Override
	public List<EUNode> getItemCatList(long parent_id) {
		
		TbItemCatExample example = new TbItemCatExample();
		
		Criteria createCriteria = example.createCriteria();
		
		createCriteria.andParentIdEqualTo(parent_id);
		
		List<TbItemCat> list = catMapper.selectByExample(example);
		
		List<EUNode> nodes = new ArrayList<>();
		
		for (TbItemCat tbItemCat : list) {
			EUNode node = new EUNode();
			
			node.setId(tbItemCat.getId());
			
			node.setText(tbItemCat.getName());
			
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			
			nodes.add(node);
		}
		
		return nodes;
	}

}
