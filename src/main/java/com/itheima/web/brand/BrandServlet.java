package com.itheima.web.brand;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;
import com.itheima.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author liweiliang
 * @create 2023-01-31 21:34
 * @description Brand的web方法定义
 */
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandService brandService = new BrandServiceImpl();

    /***
     * @details: 查询所有
     * @param: [request, response]
     * @return: void
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<Brand> brands = brandService.selectAll();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(brands);

        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    /***
     * @details: 批量删除
     * @param: [request, response]
     * @return: void
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.接收ids(JSON)
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //2.将JSON转为IDS数组
        int[] ids = JSON.parseObject(params,int[].class);

        //3.调用service方法,实现删除
        brandService.deleteByIds(ids);

        //4.响应删除成功
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.接收Brand对象,转为id
        BufferedReader reader = request.getReader();
        String param = reader.readLine();
        int id = JSON.parseObject(param, int.class);

        //2.调用service方法,实现删除
        brandService.deleteById(id);

        //3.响应删除成功
        response.getWriter().write("success");
    }


    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.接收数据,转为brand对象
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params,Brand.class);

        //2.调用service方法,实现添加
        brandService.add(brand);

        //3.响应添加成功
        response.getWriter().write("success");
    }


    /***
     * @details: 分页查询
     * @param: [request, response]
     * @return: void
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收当前页码 和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //3. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //4. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收当前页码 和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2.获取查询条件对象
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);

        //2.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        //3. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //4. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
