package com.lqinggang.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Users;

/**
 * @author LQingGang
 * @time 2018年4月17日 - 下午4:50:15
 */
public class ExportToExcelUtil {

	/**
	 * 导出鲜花信息
	 * 
	 * @param loacation
	 * @param fileName
	 * @param exporter
	 * @param flowersList
	 * @return boolean
	 */
	public static boolean exportFlowersDataToExcel(String location, String fileName, String exporter,
			List<Flowers> flowersList) {

		try {
			// 创建一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个Sheet
			HSSFSheet sheet = workbook.createSheet();
			// 创建第一行
			HSSFRow row0 = sheet.createRow(0);

			// sheet风格
			CellStyle titleStyle = workbook.createCellStyle();
			// 设置字体
			HSSFFont headfont = workbook.createFont();
			headfont.setFontName("宋体");
			headfont.setFontHeight((short) 22);
			headfont.setFontHeightInPoints((short) 22);
			headfont.setBold(true);
			titleStyle.setFont(headfont);
			titleStyle.setAlignment(HorizontalAlignment.CENTER);

			CellStyle propertyStyle = workbook.createCellStyle();
			HSSFFont propertyFont = workbook.createFont();
			propertyFont.setFontName("宋体");
			propertyFont.setFontHeight((short) 14);
			propertyFont.setFontHeightInPoints((short) 14);
			propertyFont.setBold(true);
			propertyStyle.setFont(propertyFont);
			propertyStyle.setAlignment(HorizontalAlignment.CENTER);

			CellStyle contentStyle1 = workbook.createCellStyle();
			HSSFFont contentFont1 = workbook.createFont();
			contentFont1.setFontName("宋体");
			contentFont1.setFontHeight((short) 12);
			contentFont1.setFontHeightInPoints((short) 12);
			contentStyle1.setFont(contentFont1);
			contentStyle1.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
			contentStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			contentStyle1.setAlignment(HorizontalAlignment.LEFT);

			CellStyle contentStyle2 = workbook.createCellStyle();
			HSSFFont contentFont2 = workbook.createFont();
			contentFont2.setFontName("宋体");
			contentFont2.setFontHeight((short) 12);
			contentFont2.setFontHeightInPoints((short) 12);
			contentStyle2.setFont(contentFont2);
			contentStyle2.setAlignment(HorizontalAlignment.LEFT);

			// 表头--------------
			CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, 11);
			sheet.addMergedRegion(rangeAddress); // 合并单元格
			// 设置行高
			HSSFCell headcell = row0.createCell(0);
			headcell.setCellValue("鲜花信息统计表");
			headcell.setCellStyle(titleStyle);

			// 表头--------------

			// 第三行
			HSSFRow row2 = sheet.createRow(1);
			row2.setHeightInPoints(20);
			// 第一列
			HSSFCell id = row2.createCell(0);
			id.setCellValue("鲜花编号");
			id.setCellStyle(propertyStyle);
			HSSFCell name = row2.createCell(1);
			name.setCellValue("鲜花名称");
			name.setCellStyle(propertyStyle);
			HSSFCell keyword = row2.createCell(2);
			keyword.setCellValue("搜索关键字");
			keyword.setCellStyle(propertyStyle);
			HSSFCell color = row2.createCell(3);
			color.setCellValue("颜色");
			color.setCellStyle(propertyStyle);
			HSSFCell amount = row2.createCell(4);
			amount.setCellValue("枝数");
			amount.setCellStyle(propertyStyle);
			HSSFCell origin = row2.createCell(5);
			origin.setCellValue("产地");
			origin.setCellStyle(propertyStyle);
			HSSFCell image = row2.createCell(6);
			image.setCellValue("鲜花图片");
			image.setCellStyle(propertyStyle);
			HSSFCell quantity = row2.createCell(7);
			quantity.setCellValue("库存量");
			quantity.setCellStyle(propertyStyle);
			HSSFCell price = row2.createCell(8);
			price.setCellValue("鲜花价格");
			price.setCellStyle(propertyStyle);
			HSSFCell catagory = row2.createCell(9);
			catagory.setCellValue("鲜花类别");
			catagory.setCellStyle(propertyStyle);
			HSSFCell description = row2.createCell(10);
			description.setCellValue("花语");
			description.setCellStyle(propertyStyle);
			HSSFCell content = row2.createCell(11);
			content.setCellValue("详细信息");
			content.setCellStyle(propertyStyle);
			if (flowersList != null) {
				int k = 2; // 第几行单元格
				for (int i = 0; i < flowersList.size(); i++) {
					Flowers flower = flowersList.get(i);
					HSSFRow row = sheet.createRow(k++);
					row.setHeightInPoints(18);

					HSSFCell contentcell = row.createCell(0);
					contentcell.setCellValue(flower.getFlower_id());
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell = row.createCell(1);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getName());
					contentcell = row.createCell(2);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getKeyword());
					contentcell = row.createCell(3);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getColor());
					contentcell = row.createCell(4);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getAmount());
					contentcell = row.createCell(5);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getOrigin());
					contentcell = row.createCell(6);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getImage());
					contentcell = row.createCell(7);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getQuantity());
					contentcell = row.createCell(8);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getPrice());
					contentcell = row.createCell(9);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getCategory_id().getName());
					contentcell = row.createCell(10);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getDescription());
					contentcell = row.createCell(11);
					if (i % 2 == 0) {
						contentcell.setCellStyle(contentStyle1);
					} else {
						contentcell.setCellStyle(contentStyle2);
					}
					contentcell.setCellValue(flower.getContent());
				}
			}

			int footRowNumber = sheet.getLastRowNum();

			HSSFRow footRow = sheet.createRow(footRowNumber + 2);
			HSSFCell footCell = footRow.createCell(0);
			footCell.setCellValue("导出人：" + exporter);
			rangeAddress = new CellRangeAddress(footRowNumber + 2, footRowNumber + 2, 0, 11);
			sheet.addMergedRegion(rangeAddress); // 合并单元格

			HSSFRow row1 = sheet.createRow(footRowNumber + 3);
			HSSFCell cell = row1.createCell(0);
			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			cell.setCellValue("导出时间：" + df.format(day));
			rangeAddress = new CellRangeAddress(footRowNumber + 3, footRowNumber + 3, 0, 11);
			sheet.addMergedRegion(rangeAddress); // 合并单元格

			// 文件夹不存在时创建文件夹
			File file = new File(location + fileName + ".xls");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			FileOutputStream fliFileOutputStream = new FileOutputStream(file);

			workbook.write(fliFileOutputStream);
			fliFileOutputStream.flush();
			fliFileOutputStream.close();
			workbook.close();

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 导出用户信息
	 * 
	 * @param location
	 * @param filename
	 * @param expoerter
	 * @param usersList
	 * @param contactsList
	 * @param membersList
	 * @return boolean
	 */
	public static boolean exportUsersInfoToExcel(String location, String filename, String exporter,
			List<Users> usersList, List<Contact> contactsList, List<Member> membersList) {
		try {
			// 创建一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个Sheet
			HSSFSheet sheet = workbook.createSheet();

			// 表头
			HSSFRow titleRow = sheet.createRow(0);
			CellStyle titleStyle = titleCellStyle(workbook);
			HSSFCell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("用户信息表");
			titleCell.setCellStyle(titleStyle);
			CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, 12);
			sheet.addMergedRegion(rangeAddress); // 合并单元格

			// 属性
			HSSFRow propertyRow = sheet.createRow(1);
			CellStyle propertyStyle = propertyStyle(workbook);
			propertyRow.setRowStyle(propertyStyle);
			CellStyle contentStyle1 = contentCellStyle1(workbook);
			CellStyle contentStyle2 = contentCellStyle2(workbook);

			String[] property = { "用户ID", "用户名", "性别", "年龄", "生日", "注册时间", "Email", "联系电话", "联系地址", "折率", "经验", "等级" };

			for (int i = 0; i < property.length; i++) {
				Cell cell = propertyRow.createCell(i);
				cell.setCellValue(property[i]);
			}

			if (usersList != null) {
				int k = 2; // 第几行单元格
				int j = 0;
				for (Users user : usersList) {

					HSSFRow infoRow = sheet.createRow(k++);

					List<String> info = new ArrayList<String>();
					info.add(String.valueOf(user.getUser_id().getPerson_id()));
					info.add(user.getUser_id().getName());
					info.add(user.getGender());
					info.add(String.valueOf(user.getAge()));
					info.add(String.valueOf(user.getBirtyday()));
					info.add(user.getRegister_date().toString());
					for (Contact contact : contactsList) {
						if (contact.getContact_id().getPerson_id() == user.getUser_id().getPerson_id()) {
							info.add(contact.getEmail());
							info.add(contact.getTelephone());
							info.add(contact.getAddress());
						}
					}
					for (Member member : membersList) {
						if (member.getPerson_id().getPerson_id() == user.getUser_id().getPerson_id()) {
							info.add(String.valueOf(member.getDiscount()));
							info.add(String.valueOf(member.getExperience()));
							info.add(String.valueOf(member.getRank()));
						}
					}

					for (int i = 0; i < info.size(); i++) {
						HSSFCell infoCell = infoRow.createCell(i);
						infoCell.setCellValue(info.get(i));
						if (j % 2 == 0) {
							infoCell.setCellStyle(contentStyle1);
						} else {
							infoCell.setCellStyle(contentStyle2);
						}
					}
					j++;
				}

			}
			sheetFoot(sheet, exporter, 12);
			export(workbook, location, filename);
			workbook.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 导出订单信息
	 * 
	 * @param location
	 * @param filename
	 * @param exporter
	 * @param ordersList
	 * @return boolean
	 */
	public static boolean exportOrdersInfoToExcel(String location, String filename, String exporter,
			List<Orders> ordersList) {
		try {
			// 创建一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个Sheet
			HSSFSheet sheet = workbook.createSheet();

			// 表头
			HSSFRow titleRow = sheet.createRow(0);
			CellStyle titleStyle = titleCellStyle(workbook);
			HSSFCell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("用户订单表");
			titleCell.setCellStyle(titleStyle);
			CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, 12);
			sheet.addMergedRegion(rangeAddress); // 合并单元格

			// 属性
			HSSFRow propertyRow = sheet.createRow(1);
			CellStyle propertyStyle = propertyStyle(workbook);
			propertyRow.setRowStyle(propertyStyle);
			CellStyle contentStyle1 = contentCellStyle1(workbook);
			CellStyle contentStyle2 = contentCellStyle2(workbook);

			String[] property = { "订单编号", "用户名", "收件人", "收件人联系方式", "收件地址", "鲜花编号", "鲜花名称", "数量", "价格", "订单状态", "下单时间",
					"备注" };
			for (int i = 0; i < property.length; i++) {
				Cell cell = propertyRow.createCell(i);
				cell.setCellValue(property[i]);
			}

			// for (int i = 0; i < property.length; i++) {
			// Cell cell = propertyRow.createCell(i);
			// cell.setCellValue(property[i]);
			// }
			if (ordersList != null) {
				int k = 2; // 第几行单元格
				for (int i = 0; i < ordersList.size(); i++) {
					HSSFRow infoRow = sheet.createRow(k++);

					List<String> info = new ArrayList<String>();
					info.add(ordersList.get(i).getOrder_id());
					info.add(
							ordersList.get(i).getPerson_id() == null ? "" : ordersList.get(i).getPerson_id().getName());
					info.add(ordersList.get(i).getRecipient());
					info.add(ordersList.get(i).getContact());
					info.add(ordersList.get(i).getAddress());
					info.add(String.valueOf(ordersList.get(i).getFlower_id().getFlower_id()));
					info.add(ordersList.get(i).getFlower_id().getName());
					info.add(String.valueOf(ordersList.get(i).getAmount()));
					info.add(String.valueOf(ordersList.get(i).getPrice()));
					info.add(ordersList.get(i).getStatus_type_id().getStatus_name());
					info.add(String.valueOf(ordersList.get(i).getDate()));
					info.add(ordersList.get(i).getNote());
					for (int j = 0; j < property.length; j++) {
						HSSFCell infoCell = infoRow.createCell(j);
						infoCell.setCellValue(info.get(j));
						if (i % 2 == 0) {
							infoCell.setCellStyle(contentStyle1);
						} else {
							infoCell.setCellStyle(contentStyle2);
						}
					}
				}

			}

			sheetFoot(sheet, exporter, 12);
			export(workbook, location, filename);
			workbook.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean exportEvaluationToExcel(String location, String filename, String exporter,
			List<Purchase> purchasesList) {

		try {
			// 创建一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个Sheet
			HSSFSheet sheet = workbook.createSheet();

			String[] property = { "订单编号", "用户名", "鲜花编号", "鲜花名称", "物流评价", "商品评价", "服务评价", "评价时间", "评价内容" };

			// 表头
			HSSFRow titleRow = sheet.createRow(0);
			CellStyle titleStyle = titleCellStyle(workbook);
			HSSFCell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("鲜花评价表");
			titleCell.setCellStyle(titleStyle);
			CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, property.length);
			sheet.addMergedRegion(rangeAddress); // 合并单元格

			// 属性
			HSSFRow propertyRow = sheet.createRow(1);
			CellStyle propertyStyle = propertyStyle(workbook);
			propertyRow.setRowStyle(propertyStyle);
			CellStyle contentStyle1 = contentCellStyle1(workbook);
			CellStyle contentStyle2 = contentCellStyle2(workbook);

			for (int i = 0; i < property.length; i++) {
				Cell cell = propertyRow.createCell(i);
				cell.setCellValue(property[i]);
			}
			if (purchasesList != null && purchasesList.size() > 0) {
				int k = 2; // 第几行单元格
				for (int i = 0; i < purchasesList.size(); i++) {
					HSSFRow infoRow = sheet.createRow(k++);

					List<String> info = new ArrayList<String>();
					info.add(purchasesList.get(i).getOrder_id().getOrder_id());
					info.add(purchasesList.get(i).getOrder_id().getPerson_id().getName());
					info.add(String.valueOf(purchasesList.get(i).getOrder_id().getFlower_id().getFlower_id()));
					info.add(purchasesList.get(i).getOrder_id().getFlower_id().getName());
					info.add(String.valueOf(purchasesList.get(i).getLogistics()));
					info.add(String.valueOf(purchasesList.get(i).getService()));
					info.add(String.valueOf(purchasesList.get(i).getCommodity()));
					info.add(purchasesList.get(i).getDate().toString());
					info.add(purchasesList.get(i).getPurchase_content());

					for (int j = 0; j < property.length; j++) {
						HSSFCell infoCell = infoRow.createCell(j);
						infoCell.setCellValue(info.get(j));
						if (i % 2 == 0) { // 设置风格
							infoCell.setCellStyle(contentStyle1);
						} else {
							infoCell.setCellStyle(contentStyle2);
						}
					}

				}
			}
			sheetFoot(sheet, exporter, property.length);
			export(workbook, location, filename);
			workbook.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * 表格底部
	 * 
	 * @param sheet
	 * @param exporter
	 * @param number
	 * @return void
	 */
	private static void sheetFoot(HSSFSheet sheet, String exporter, int number) {
		int footRowNumber = sheet.getLastRowNum();

		HSSFRow footRow = sheet.createRow(footRowNumber + 2);
		HSSFCell footCell = footRow.createCell(0);
		footCell.setCellValue("导出人：" + exporter);
		CellRangeAddress rangeAddress = new CellRangeAddress(footRowNumber + 2, footRowNumber + 2, 0, number);
		sheet.addMergedRegion(rangeAddress); // 合并单元格

		HSSFRow row1 = sheet.createRow(footRowNumber + 3);
		HSSFCell cell = row1.createCell(0);
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		cell.setCellValue("导出时间：" + df.format(day));
		rangeAddress = new CellRangeAddress(footRowNumber + 3, footRowNumber + 3, 0, number);
		sheet.addMergedRegion(rangeAddress); // 合并单元格
	}

	/**
	 * 内容风格2
	 * 
	 * @param workbook
	 * @return CellStyle
	 */
	private static CellStyle contentCellStyle2(HSSFWorkbook workbook) {
		CellStyle contentStyle2 = workbook.createCellStyle();
		HSSFFont contentFont2 = workbook.createFont();
		contentFont2.setFontName("宋体");
		contentFont2.setFontHeight((short) 12);
		contentFont2.setFontHeightInPoints((short) 12);
		contentStyle2.setFont(contentFont2);
		contentStyle2.setAlignment(HorizontalAlignment.LEFT);
		return contentStyle2;

	}

	/**
	 * 内容风格1
	 * 
	 * @param workbook
	 * @return CellStyle
	 */
	private static CellStyle contentCellStyle1(HSSFWorkbook workbook) {
		CellStyle contentStyle1 = workbook.createCellStyle();
		HSSFFont contentFont1 = workbook.createFont();
		contentFont1.setFontName("宋体");
		contentFont1.setFontHeight((short) 12);
		contentFont1.setFontHeightInPoints((short) 12);
		contentStyle1.setFont(contentFont1);
		contentStyle1.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		contentStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		contentStyle1.setAlignment(HorizontalAlignment.LEFT);
		return contentStyle1;
	}

	/**
	 * 属性风格
	 * 
	 * @param workbook
	 * @return CellStyle
	 */
	private static CellStyle propertyStyle(HSSFWorkbook workbook) {
		CellStyle propertyStyle = workbook.createCellStyle();
		HSSFFont propertyFont = workbook.createFont();
		propertyFont.setFontName("宋体");
		propertyFont.setFontHeight((short) 14);
		propertyFont.setFontHeightInPoints((short) 14);
		propertyFont.setBold(true);
		propertyStyle.setFont(propertyFont);
		propertyStyle.setAlignment(HorizontalAlignment.CENTER);
		return propertyStyle;
	}

	/**
	 * 表头风格
	 * 
	 * @param workbook
	 * @return CellStyle
	 */
	private static CellStyle titleCellStyle(HSSFWorkbook workbook) {
		CellStyle titleStyle = workbook.createCellStyle();
		HSSFFont headfont = workbook.createFont();
		headfont.setFontName("宋体");
		headfont.setFontHeight((short) 22);
		headfont.setFontHeightInPoints((short) 22);
		headfont.setBold(true);
		titleStyle.setFont(headfont);
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		return titleStyle;
	}

	/**
	 * 输出到文件
	 * 
	 * @param workbook
	 * @param location
	 * @param filename
	 * @return void
	 */
	private static void export(HSSFWorkbook workbook, String location, String filename) {
		// 文件夹不存在时创建文件夹
		File file = new File(location + filename + ".xls");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		try {
			FileOutputStream fliFileOutputStream = new FileOutputStream(file);

			workbook.write(fliFileOutputStream);
			fliFileOutputStream.flush();
			fliFileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
