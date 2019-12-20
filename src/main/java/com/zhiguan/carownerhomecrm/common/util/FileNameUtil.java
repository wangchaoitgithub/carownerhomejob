package com.zhiguan.carownerhomecrm.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.coobird.thumbnailator.Thumbnails;

import com.zhiguan.commonNew.mail.RandomSecurityCode;
import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.commonNew.util.FileUtil;
import com.zhiguan.carownerhomecrm.common.constant.Config;
 
public class FileNameUtil {
	
	/**
	 * 给图片统一生产名称
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-12-3
	 * @param userId
	 * @return
	 */
	public static String getImgName(String userId){
		return DateFormatUtil.formatToStringTimeDetail(new Date()) + "_" + userId + "_" + RandomSecurityCode.getSecurityCode();
	}
	
	
	public static void main(String[] args) throws Exception{
		//System.out.println(getMinUrlAndSaveMin("E:\\test\\tanwenping.jpg"));
	}
	
	/**
	 * 生成小文件，并生成小图片地址
	 * @remark 
	 * @author shengfukang
	 * @createDate 2015-1-22
	 * @param fileMaxUrl
	 * @return
	 * @throws IOException
	 */
	public static String getMinUrlAndSaveMin(String fileMaxUrl) throws Exception{
		String minFileName = FileUtil.getFileNameNoSuffix(fileMaxUrl) + "_min." + FileUtil.getFileSuffix(fileMaxUrl);
		String minFileDir = FileUtil.getFileDir(fileMaxUrl);
		//压缩成一个小图片
		File fileMax = new File(fileMaxUrl);
		if(FileUtil.getKbSize(fileMax) > 50){//当用户上传的图片>50kb的时候才压缩成小图片，否则小图片复制大图片
			Thumbnails.of(fileMax).size(Config.minImgWideSize, Config.minImgHighSize).toFile(new File(minFileDir,minFileName));
		}else{
			FileUtil.copy(fileMax, minFileDir + File.separator + minFileName);
		}
		return minFileDir + File.separator + minFileName;
	}
	
}
