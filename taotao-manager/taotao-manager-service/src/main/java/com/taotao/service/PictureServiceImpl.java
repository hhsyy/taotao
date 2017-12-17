package com.taotao.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.util.FtpUtil;
import com.taotao.common.util.IDUtils;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FILE_ADDR}")
	private String FILE_ADDR;
	@Value("${FILE_USER}")
	private String FILE_USER;
	@Value("${FILE_PASSWORD}")
	private String FILE_PASSWORD;
	@Value("${FILE_PORT}")
	private Integer FILE_PORT;
	@Value("${FILE_BASE_ADDR}")
	private String FILE_BASE_ADDR;
	@Value("${File_RETUEN_ADDR}")
	private String File_RETUEN_ADDR;
	
	
	@Override
	public Map uploadFile(MultipartFile file) {
		Map resultMap = new HashMap<>();
		
			
			String oldname = file.getOriginalFilename();
			
			String genImageName = IDUtils.genImageName();
			
			String path = new DateTime().toString("yyyy/mm/dd");
			
			String newname =genImageName+oldname.substring(oldname.lastIndexOf("."));
			try {
			boolean uploadFile = FtpUtil.uploadFile(FILE_ADDR, FILE_PORT, FILE_USER,FILE_PASSWORD, FILE_BASE_ADDR, 
					path, newname, file.getInputStream());
	
			if (!uploadFile) {
				resultMap.put("error",1 );
				resultMap.put("message", "无数据");
				return resultMap;
			}
			resultMap.put("error",0 );
			resultMap.put("url", File_RETUEN_ADDR+path+"/"+newname);
			
			return resultMap;
			
		} catch (Exception e) {
			resultMap.put("error",1 );
			resultMap.put("message","上传失败");
			return resultMap;
		}
	}

}
