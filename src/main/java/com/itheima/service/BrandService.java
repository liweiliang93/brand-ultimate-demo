package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liweiliang
 * @create 2023-01-31 21:17
 * @description service接口:后续所有关于Brand的service都实现于此接口;其余业务也是如此
 */
public interface BrandService {

    /***
     * @details: 查询所有
     * @param: []
     * @return: java.util.List<com.itheima.pojo.Brand>
     */
    List<Brand> selectAll();

    /**
     * @details: 批量删除
     * @param: [ids]
     * @return: void
     */
    void deleteByIds(int[] ids);

    /***
     * @details: 单个删除
     * @param: [id]
     * @return: void
     */
    void deleteById(int id);

    /*** 
     * @details: 分页查询
     * @param: [currentPage:当前页码, pageSize:每页显示条数]
     * @return: com.itheima.pojo.PageBean<com.itheima.pojo.Brand>
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    /***
     * @details: 增加产品
     * @param: [brand]
     * @return: void
     */
    void add(Brand brand);

    /***
     * @details:  分页条件查询
     * @param: [currentPage, pageSize, brand]
     * @return: com.itheima.pojo.PageBean<com.itheima.pojo.Brand>
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);
}
