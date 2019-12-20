package com.zhiguan.carownerhomecrm.action.common;

import com.zhiguan.commonNew.constant.SmsConstant;
import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.carownerhomecrm.domain.phone.PhoneSmsRecord;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class excelUtil {
	/**   
	*    
	* 方法描述：短信客户：导出消费数据
	* 创建人：yipeng
	* 创建时间：2018-11-02   
	* @version        
	*/
	public static HSSFCellStyle creatCellStyle(HSSFWorkbook book,short size){
		//创建单元格样式
		HSSFCellStyle cellStyle = book.createCellStyle();
		//设置居中左右对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//设置上下居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//创建字体单元
		HSSFFont font = book.createFont();
		//设置字体大小
		font.setFontHeightInPoints(size);
		//创建字体颜色 
		font.setColor(HSSFColor.BROWN.index);
		//字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//设置新商品样式
		cellStyle.setFont(font);
		return cellStyle;
	}
	public static void exportOrder(List<PhoneSmsRecord> list, ServletOutputStream outputStream){
		//创建excel文件 
		HSSFWorkbook book = new HSSFWorkbook();
		//创建excel文件中的一个页面 
		HSSFSheet sheet = book.createSheet("短信平台数据");
		//设置默认宽度25
		sheet.setDefaultColumnWidth(25);
		//合并单元格
		CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, 6);
		//页添加单元格 
		sheet.addMergedRegion(rangeAddress);
		//创建第1行 
		HSSFRow createRow = sheet.createRow(0);
		//创建名称行样式
		HSSFCellStyle style = creatCellStyle(book,(short)16);
		//创建标题行样式
		HSSFCellStyle style1 = creatCellStyle(book,(short)11);
		//创建第0个单元格 
		HSSFCell Cell = createRow.createCell(0);
		//设置单元格的样式 
		Cell.setCellStyle(style);
		//创建单元格的内容 
		if(list != null  && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				Cell.setCellValue("短信平台数据");
			}
		}else{
			Cell.setCellValue("暂无数据");
			try {
				book.write(outputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		//创建第一行单元格 
		HSSFRow Row1 = sheet.createRow(1);
		//定义标题行单元格的值 
		String [] tille = {"获取手机号时间","获取验证码时间","验证码","手机号码","UUID","应用名称","状态"};
		//循环遍历设置题行单元格的值 
		for (int i = 0; i < tille.length; i++) {
			HSSFCell createCell = Row1.createCell(i);
			createCell.setCellStyle(style1);
			createCell.setCellValue(tille[i]);
		}
		//遍历List 设置优惠券记录的值
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				HSSFRow Row = sheet.createRow(i+2);
				HSSFCell createCell = Row.createCell(0);
				createCell.setCellValue(DateFormatUtil.formatToStringTime(list.get(i).getGetTime()));
				HSSFCell createCel2 = Row.createCell(1);
				createCel2.setCellValue(DateFormatUtil.formatToStringTime(list.get(i).getDoneTime()));
				HSSFCell createCel3 = Row.createCell(2);
				createCel3.setCellValue(list.get(i).getVerificationCode());
				HSSFCell createCel4 = Row.createCell(3);
				createCel4.setCellValue(list.get(i).getPhoneNumber());
				HSSFCell createCel5 = Row.createCell(4);
				createCel5.setCellValue(list.get(i).getUuid());
				HSSFCell createCel6 = Row.createCell(5);
				createCel6.setCellValue(list.get(i).getApplyName());
				HSSFCell createCel7 = Row.createCell(6);
				String stateStr = null;
				String state = list.get(i).getState();
				if(SmsConstant.SMS_DONE.equals(state)) {
					stateStr = "已消费：获取成功";
				}else {
					stateStr = "未消费：失败原因（";
					if(SmsConstant.SMS_INIT.equals(state)) {
						stateStr += "已获取手机号，未进行后面的操作）";
					}
					if(SmsConstant.SMS_SEND.equals(state)) {
						stateStr += "已通知平台，运营商没有发送验证码，未进行后面的操作）";
					}
					if(SmsConstant.SMS_RECEIVE.equals(state)) {
						stateStr += "运营商已发送验证码，你没有获取验证码）";
					}
				}
				createCel7.setCellValue(stateStr);
			}
		}
		try {
			book.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
