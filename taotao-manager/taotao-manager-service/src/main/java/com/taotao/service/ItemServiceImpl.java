package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.IDUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamItem;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	public TbItemMapper itemMapper;

	@Autowired
	public TbItemDescMapper itemDescMapper;

	@Autowired
	public TbItemParamMapper itemParamMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(long id) {

		TbItemExample example = new TbItemExample();

		Criteria createCriteria = example.createCriteria();

		createCriteria.andIdEqualTo(id);

		List<TbItem> list = itemMapper.selectByExample(example);

		if (list.size() > 0 && list != null) {
			TbItem tbItem = list.get(0);
			return tbItem;
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {

		TbItemExample example = new TbItemExample();

		PageHelper.startPage(page, rows);

		List<TbItem> list = itemMapper.selectByExample(example);
		
		EUDataGridResult dataGridResult = new EUDataGridResult();

		dataGridResult.setRows(list);

		PageInfo<TbItem> pageInfo = new PageInfo<>(list);

		dataGridResult.setTotal(pageInfo.getTotal());

		return dataGridResult;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc,String itemParam) {

		long genItemId = IDUtils.genItemId();

		Date date = new Date();

		item.setId(genItemId);

		item.setStatus((byte) 1);

		item.setCreated(date);

		item.setUpdated(date);

		TaotaoResult taotaoResult = insertDesc(genItemId, desc, date);

		if (taotaoResult.getStatus() != 200) {

			System.out.println("插入描述信息错误");
		}
		
		taotaoResult = createParam(genItemId, itemParam,desc, date);
		if (taotaoResult.getStatus() != 200) {

			System.out.println("插入描述信息错误");
		}

		itemMapper.insert(item);

		return TaotaoResult.ok();
	}

	public TaotaoResult insertDesc(long id, String desc, Date date) {

		TbItemDesc d = new TbItemDesc();

		d.setItemId(id);

		d.setCreated(date);

		d.setItemDesc(desc);

		d.setUpdated(date);

		itemDescMapper.insert(d);

		return TaotaoResult.ok();

	}

	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {

		TbItemParamExample example = new TbItemParamExample();

		PageHelper.startPage(page, rows);

		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

		EUDataGridResult dataGridResult = new EUDataGridResult();
		
		dataGridResult.setRows(list);

		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);

		dataGridResult.setTotal(pageInfo.getTotal());

		return dataGridResult;
	}
	
	public TaotaoResult createParam(Long itemId, String itemParam,String desc,Date date){
		TbItemParamItem paramItem = new TbItemParamItem();
		
		paramItem.setId(itemId);
		
		paramItem.setParamData(itemParam);
		
		paramItem.setCreated(date);
		
		paramItem.setUpdated(date);
		
		itemParamItemMapper.insert(paramItem);
		
		return TaotaoResult.ok();
		
	}

}
