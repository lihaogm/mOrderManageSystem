package com.lihaogn.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.lihaogn.domain.Food;
import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
import com.lihaogn.service.FoodService;
import com.lihaogn.utils.JedisPoolUtils;
import com.lihaogn.vo.Condition;
import com.lihaogn.vo.PageBean;
import com.lihaogn.vo.PageBeanFood;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class FoodServlet
 */
public class FoodServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 获取菜品列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getFoodList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FoodService service = new FoodService();

		// 分页展示菜品

		// 获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null) {
			currentPageStr = "1";
		}
		int currentPage = Integer.parseInt(currentPageStr);
		// 默认每页10条
		int currentCount = 10;

		PageBean pageBean = null;

		pageBean = service.getPageBean(currentPage, currentCount);

		request.setAttribute("pageBean", pageBean);

		request.getRequestDispatcher("/food_list.jsp").forward(request, response);
	}

	/**
	 * 添加菜品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Food food = new Food();
		// 收集数据的容器
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 接收文件上传
			// 1 创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2 创建文件上传核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 3 解析request获得文件项对象集合
			List<FileItem> parseRequest = upload.parseRequest(request);
			// 4 遍历文件项集合
			for (FileItem item : parseRequest) {
				// 5 判断普通表单项/文件上传项
				boolean formField = item.isFormField();
				if (formField) {
					// 普通表单项
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");

					map.put(fieldName, fieldValue);
				} else {
					// 文件上传项
					String fileName = item.getName();
					String path = this.getServletContext().getRealPath("images/food_menu");
					InputStream inputStream = item.getInputStream();
					OutputStream outputStream = new FileOutputStream(path + "/" + fileName);
					IOUtils.copy(inputStream, outputStream);
					inputStream.close();
					outputStream.close();
					// 删除临时文件
					item.delete();

					map.put("fimage", "images/food_menu/" + fileName);
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
		// 设置id
		food.setPk_fid(UUID.randomUUID().toString());
		// 设置修改时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = dateFormat.format(new Date());
		food.setFmodified_date(createDate);
		food.setFcreat_date(createDate);

//		System.out.println(food);

		FoodService service = new FoodService();
		service.addFood(food);

		response.sendRedirect(request.getContextPath() + "/food?method=getFoodList");
	}

	/**
	 * 删除菜品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fcId = request.getParameter("fcId");
		FoodService foodService = new FoodService();
		foodService.deleteFoodById(fcId);
		response.sendRedirect(request.getContextPath() + "/food?method=getFoodList");
	}

	/**
	 * 批量删除菜品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteMultiFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fcIds = request.getParameter("fcids");
		FoodService foodService = new FoodService();
		foodService.deleteMutilFoodById(fcIds);

		response.sendRedirect(request.getContextPath() + "/food?method=getFoodList");
	}

	/**
	 * 准备编辑菜品需要的数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editFoodPrepared(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// 获取要查询的fid，查询要更改的food信息并回显
		String fid = request.getParameter("fid");

		FoodService foodService = new FoodService();
		// 获取页面需要的food信息
		PageBeanFood pagefood = foodService.getFoodById(fid);
		request.setAttribute("food", pagefood);

		request.getRequestDispatcher("/food_edit.jsp").forward(request, response);
	}

	/**
	 * 编辑菜品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Food food = new Food();
		// 收集数据的容器
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 接收文件上传
			// 1 创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2 创建文件上传核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 3 解析request获得文件项对象集合
			List<FileItem> parseRequest = upload.parseRequest(request);
			// 4 遍历文件项集合
			for (FileItem item : parseRequest) {
				// 5 判断普通表单项/文件上传项
				boolean formField = item.isFormField();
				if (formField) {
					// 普通表单项
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");

					map.put(fieldName, fieldValue);
				} else {
					// 文件上传项
					String fileName = item.getName();
					String path = this.getServletContext().getRealPath("images/food_menu");
					InputStream inputStream = item.getInputStream();
					OutputStream outputStream = new FileOutputStream(path + "/" + fileName);
					IOUtils.copy(inputStream, outputStream);
					inputStream.close();
					outputStream.close();
					// 删除临时文件
					item.delete();

					map.put("fimage", "images/food_menu/" + fileName);
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
		response.sendRedirect(request.getContextPath() + "/food?method=getFoodList");
	}

	/**
	 * 搜索菜品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		request.setCharacterEncoding("utf-8");

		// 1 收集表单数据
		Map<String, String[]> properties = request.getParameterMap();
		// 2 将数据封装到vo实体中
		Condition condition = new Condition();
		try {
			BeanUtils.populate(condition, properties);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 判断搜索条件是否为空，为空直接返回列表页
		if ("".equals(condition.getFoodCookCategory()) && "".equals(condition.getFoodTypeCategory())
				&& "".equals(condition.getFoodName().trim())) {
			response.sendRedirect(request.getContextPath() + "/food?method=getFoodList");
			return;
		}

		// 获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null) {
			currentPageStr = "1";
		}
		int currentPage = Integer.parseInt(currentPageStr);
		// 默认每页10条
		int currentCount = 10;

		// 3 将实体传递给service层
		FoodService foodService = new FoodService();

		PageBean pageBean = null;

		pageBean = foodService.getPageBeanByCondition(currentPage, currentCount, condition);

		request.setAttribute("pageBean", pageBean);

		// 获得所有菜品种类
		List<FoodCategory> listFoodCategory = foodService.getAllFoodCategory();
		request.setAttribute("foodCategories", listFoodCategory);

		// 获得荤素种类
		List<FoodType> listFoodType = foodService.getAllFoodType();
		request.setAttribute("foodTypes", listFoodType);

		request.setAttribute("condition", condition);

		request.getRequestDispatcher("/food_list.jsp").forward(request, response);
	}

	/**
	 * 动态显示搜索菜品关键字
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchFoodShowTip(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获得关键字
		String word = request.getParameter("word");
//				System.out.println(word);

		FoodService foodService = new FoodService();
		List<Object> foodList = null;
		try {
			foodList = foodService.findFoodByWord(word);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		String json = gson.toJson(foodList);
//				System.out.println(json);

		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().write(json);
	}

	// ------------------------------------------------------------------------------
	// 对菜品分类进行操作
	// ------------------------------------------------------------------------------

	/**
	 * 添加菜品分类
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addFoodCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		request.setCharacterEncoding("utf-8");

		String foodCategoryName = request.getParameter("category_name");
		FoodService service = new FoodService();
		FoodCategory foodCategory = new FoodCategory();

		foodCategory.setPk_fcwc_id(UUID.randomUUID().toString());
		foodCategory.setFcwc_name(foodCategoryName);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = dateFormat.format(new Date());
		foodCategory.setFcwc_create_time(createDate);
		foodCategory.setFcwc_modified_time(createDate);

		service.addFoodCategory(foodCategory);

		response.sendRedirect(request.getContextPath() + "/food?method=getFoodCategory");
	}

	/**
	 * 删除菜品分类
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteFoodCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fcId = request.getParameter("fc_id");
		FoodService service = new FoodService();
		service.deleteFoodCategoryById(fcId);
		response.sendRedirect(request.getContextPath() + "/food?method=getFoodCategory");
	}

	/**
	 * 通过ajax获取菜品分类
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getFoodCategoryByAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FoodService foodService = new FoodService();

		// 先从缓存中查询foodCategoryList 如果有直接使用, 没有再从数据库中查询 存到缓存中
		// 1、获得jedis对象 连接redis数据库
		Jedis jedis = JedisPoolUtils.getJedis();
		String foodCategoryListJson = jedis.get("FoodCategoryListJson");

		// 2、判断foodCategoryListJson是否为空
		if (foodCategoryListJson == null) {
			System.out.println("缓存没有数据 查询数据库");
			// 准备分类数据
			List<FoodCategory> listFoodCategory = foodService.getAllFoodCategory();
			Gson gson = new Gson();
			foodCategoryListJson = gson.toJson(listFoodCategory);
			jedis.set("FoodCategoryListJson", foodCategoryListJson);

			System.out.println("redis中菜品类别json： " + foodCategoryListJson);
		}

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(foodCategoryListJson);
	}

	/**
	 * 获取菜品分类
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getFoodCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FoodService service = new FoodService();

		List<FoodCategory> listFoodCategory = service.getAllFoodCategory();
//		for (FoodCategory foodCategory : listFoodCategory) {
//			System.out.println(foodCategory);
//		}
		request.setAttribute("foodCategories", listFoodCategory);
		request.getRequestDispatcher("/food_category.jsp").forward(request, response);
	}

	/**
	 * 批量删除菜品分类
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteMultiFoodCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fcIds = request.getParameter("fcids");
		FoodService service = new FoodService();
		service.deleteMutilFoodCategoryById(fcIds);
		response.sendRedirect(request.getContextPath() + "/food?method=getFoodCategory");
	}

	
	/**
	 * 编辑菜品分类前准备数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editFoodCategoryPrepared(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fcId = request.getParameter("fcid");

		FoodService service = new FoodService();
		String fcName = service.getFoodCategoryByid(fcId);
		request.setAttribute("fc_name", fcName);
		request.setAttribute("fcid", fcId);
		request.getRequestDispatcher("/food_category_edit.jsp").forward(request, response);
	}
	
	/**
	 * 编辑菜品分类
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editFoodCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		request.setCharacterEncoding("utf-8");

		String fcId = request.getParameter("fcid");
		String categoryName = request.getParameter("category_name");

		FoodCategory foodCategory = new FoodCategory();
		foodCategory.setPk_fcwc_id(fcId);
		foodCategory.setFcwc_name(categoryName);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String modifiedDate = dateFormat.format(new Date());
		foodCategory.setFcwc_modified_time(modifiedDate);

		FoodService service = new FoodService();
		service.editFoodCategory(foodCategory);

		response.sendRedirect(request.getContextPath() + "/food?method=getFoodCategory");
	}

	/**
	 * 通过ajax获取菜品荤素种类
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getFoodTypeByAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FoodService foodService = new FoodService();

		// 先从缓存中查询foodCategoryList 如果有直接使用, 没有再从数据库中查询 存到缓存中
		// 1、获得jedis对象 连接redis数据库
		Jedis jedis = JedisPoolUtils.getJedis();
		String foodTypeListJson = jedis.get("FoodTypeListJson");

		if (foodTypeListJson == null) {
			System.out.println("缓存没有数据 查询数据库");
			// 准备分类数据
			List<FoodType> listFoodType = foodService.getAllFoodType();
			Gson gson = new Gson();
			foodTypeListJson = gson.toJson(listFoodType);
			jedis.set("FoodTypeListJson", foodTypeListJson);

			System.out.println("redis中菜品荤素类别json： " + foodTypeListJson);
		}

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(foodTypeListJson);
	}
}
