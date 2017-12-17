package com.taotao.rest.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.rest.redis.dao.JedisClient;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	public TbContentMapper contentMapper;
	@Autowired
	public JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	public String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentListById(long contentCid) {
		
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
			if (!StringUtils.isBlank(result)) {
				//把字符串转换成list
				System.out.println("yi使用");
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据内容分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//向缓存中添加内容
				try {
					//把list转换成字符串
					String cacheString = JsonUtils.objectToJson(list);
					jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return list;
	}
}
