package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
@Service
public class ItemParamServiceimpl implements ItemParamService {


	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TaotaoResult getItemParamById(long id ) {
		
		TbItemParamExample example = new TbItemParamExample();
		
		Criteria createCriteria = example.createCriteria();
		
		Criteria criteria = createCriteria.andItemCatIdEqualTo(id);
		
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
				Date date = new Date();
				itemParam.setCreated(date);
				itemParam.setUpdated(date);
				//插入到规格参数模板表
				itemParamMapper.insert(itemParam);
				return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		//创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//向表中插入数据
		itemParamItemMapper.insert(itemParamItem);
		
		return TaotaoResult.ok();	
	}
	

}
