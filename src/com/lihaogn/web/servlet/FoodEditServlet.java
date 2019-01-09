package com.lihaogn.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.lihaogn.domain.Food;
import com.lihaogn.service.FoodService;

/**
 * Servlet implementation class FoodEditServlet
 */
public class FoodEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Food food = new Food();
		// 收集数据的容器
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			// 接收文件上传
			// 1 创建磁盘文件项工厂
			DiskFileItemFactory factory=new DiskFileItemFactory();
			// 2 创建文件上传核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 3 解析request获得文件项对象集合
			List<FileItem> parseRequest = upload.parseRequest(request);
			// 4 遍历文件项集合
			for (FileItem item : parseRequest) {
				// 5 判断普通表单项/文件上传项
				boolean formField=item.isFormField();
				if (formField) {
					// 普通表单项
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					
					map.put(fieldName, fieldValue);
				}else {
					// 文件上传项
					String fileName = item.getName();
					String path = this.getServletContext().getRealPath("images/food_menu");
					InputStream inputStream = item.getInputStream();
					OutputStream outputStream = new FileOutputStream(path+"/"+fileName);
					IOUtils.copy(inputStream, outputStream);
					inputStream.close();
					outputStream.close();
					// 删除临时文件
					item.delete();
					
					map.put("fimage", "images/food_menu/"+fileName);
				}
			}
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		
		try {
			BeanUtils.populate(food, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
//		System.out.println(food);
		// 3 手动设置没有的数据
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String modifiedDate = dateFormat.format(new Date());
		food.setFmodified_date(modifiedDate);
		// 4 service层
		FoodService foodService = new FoodService();
		foodService.editFood(food);
		// 5 跳转页面
		response.sendRedirect(request.getContextPath() + "/foodList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
