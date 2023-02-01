package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author liweiliang
 * @create 2023-01-31 21:37
 * @description 实现BrandService接口
 */
public class BrandServiceImpl implements BrandService {

    //0.获取SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * @details: 查询所有
     * @param: []
     * @return: java.util.List<com.itheima.pojo.Brand>
     */
    @Override
    public List<Brand> selectAll() {

        //1.开启事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.获取代理
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3.执行查询所有
        return mapper.selectAll();
    }


    /***
     * @details: 批量删除
     * @param: [ids]
     * @return: void
     */
    @Override
    public void deleteByIds(int[] ids) {

        //1.开启事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.获取代理
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3.按id数组执行删除
        mapper.deleteByIds(ids);

        //4.提交事务
        sqlSession.commit();

        //5.关闭资源
        sqlSession.close();

    }


    /***
     * @details: 单个删除
     * @param: [id]
     * @return: void
     */
    @Override
    public void deleteById(int id) {
        //1.开启事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.获取代理
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3.按id执行删除
        mapper.deleteById(id);

        //4.提交事务
        sqlSession.commit();

        //5.关闭资源
        sqlSession.close();
    }

    /***
     * @details: 按页查询
     * @param: [currentPage, pageSize]
     * @return: com.itheima.pojo.PageBean<com.itheima.pojo.Brand>
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //1.开启事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.获取代理
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3.计算开始索引、每页条目数,获取查询的Brands数组
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        List<Brand> rows = mapper.selectByPage(begin, size);

        //4.获取总条目数
        int totalCount = mapper.selectTotalCount();

        //5.封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //6.关闭资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public void add(Brand brand) {
        //1.开启事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.获取代理
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3.调用方法
        mapper.add(brand);

        //4.提交事务
        sqlSession.commit();

        //5.关闭资源
        sqlSession.close();
    }

    /***
     * @details: 分页条件查询
     * @param: [currentPage, pageSize, brand]
     * @return: com.itheima.pojo.PageBean<com.itheima.pojo.Brand>
     */
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //1.开启事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.获取代理
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3.计算开始索引、每页条目数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        //4.处理brand,使用模糊查询
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }

        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        //5.获取查询的Brands数组
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);

        //6.获取总条目数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //7.封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8.关闭资源
        sqlSession.close();

        return pageBean;
    }

}
