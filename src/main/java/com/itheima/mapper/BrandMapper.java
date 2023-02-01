package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liweiliang
 * @create 2023-01-31 20:50
 * @description BrandMapper接口:方法定义
 */
public interface BrandMapper {

    /***
     * @details: 查询所有
     * @param: []
     * @return: java.util.List<com.itheima.pojo.Brand>
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /***
     * @details: 批量删除
     * @param: [ids]
     * @return: void
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * @details: 单个删除
     * @param: [id]
     * @return: void
     */
    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(int id);

    /***
     * @details: 分页查询
     * @param: [begin:开始索引, size:查询条目数]
     * @return: java.util.List<com.itheima.pojo.Brand>:Brand数组
     */
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /***
     * @details: 查询总记录数
     * @param: []
     * @return: int:总记录数
     */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    /***
     * @details: 增加产品
     * @param: [brand]
     * @return: void
     */
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /***
     * @details:   分页条件查询
     * @param: [begin, size, brand]
     * @return: java.util.List<com.itheima.pojo.Brand>
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size,@Param("brand") Brand brand);

    /***
     * @details: 条件查询总数
     * @param: [brand]
     * @return: int
     */
    int selectTotalCountByCondition(Brand brand);

}
