package com.love.origami.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.love.origami.common.FilesVO;
import com.love.origami.common.MongoDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Service
public class FileService {
	@Resource
	private MongoDB mongoDB;
	public static final Logger logger=LoggerFactory.getLogger(FileService.class);

	public String savefile(FilesVO file,HttpServletRequest request){
		logger.info("保存文件开始");
		/**
		 * 首先将文件缓存到服务器上
		 */
		MultipartFile file3 = file.getFile();
		String fileName = file3.getOriginalFilename();
		String realPath = request.getSession().getServletContext()
        .getRealPath(fileName);
		File file2=new File(realPath);
		try {
			file3.transferTo(file2);
		} catch (IllegalStateException | IOException e1) {
			logger.info("文件保存时发生异常"+e1);
			return "error";
		}
		/**
		 * 然后将文件存如mongoDB
		 */
		MongoTemplate mongoTemplate = mongoDB.getMongoTemplate();
		DB db = mongoTemplate.getDb();
		GridFS gf=new GridFS(db,"files");
		try {
			GridFSInputFile gfs = gf.createFile(file2);
			gfs.put("_id", UUID.randomUUID().toString().replace("-", ""));  
            gfs.put("whose", file.getWhose());  
            gfs.save();  
		} catch (IOException e) {
			logger.warn("保存文件时出现异常"+e);
			logger.info("保存文件结束");
			return "error";
		}
		/**
		 * 删除缓存的文件
		 */
		if (file2.isFile()) {
			file2.delete();
        }
		logger.info("保存文件结束");
		return "success";
	}
	public void getFile(String id,OutputStream out,HttpServletResponse response) throws UnsupportedEncodingException{
		MongoTemplate mongoTemplate = mongoDB.getMongoTemplate();
		DB db = mongoTemplate.getDb();
		GridFS gf=new GridFS(db,"files");
		DBObject query  = new BasicDBObject("_id", id);
		GridFSDBFile file = gf.findOne(query);
		String filename = file.getFilename();
		response.setHeader("Content-disposition",
	            String.format("attachment; filename=\"%s\"", new String(filename.getBytes("UTF-8"),"ISO-8859-1")));
		long length = file.getLength();
	    response.setHeader("Content-Length", String.valueOf(length));
		try {
			file.writeTo(out);
		} catch (IOException e) {
			logger.warn("下载停止");
		}
	}
	public List<FilesVO> searchAll(){
		MongoTemplate mongoTemplate = mongoDB.getMongoTemplate();
		DB db = mongoTemplate.getDb();
		GridFS gf=new GridFS(db,"files");
		List<GridFSDBFile> list = gf.find(new BasicDBObject());
		List<FilesVO> list2=new ArrayList<FilesVO>();
		for (GridFSDBFile gridFSDBFile : list) {
			FilesVO file=new FilesVO();
			file.set_id(gridFSDBFile.getId().toString());
			file.setFilename(gridFSDBFile.getFilename());
			file.setWhose(gridFSDBFile.get("whose").toString());
			list2.add(file);
		}
		return list2;
	}
	public boolean deleteFile(String id){
		MongoTemplate mongoTemplate = mongoDB.getMongoTemplate();
		DB db = mongoTemplate.getDb();
		GridFS gf=new GridFS(db,"files");
		gf.remove(new BasicDBObject("_id", id));
		return true;
	}
}
