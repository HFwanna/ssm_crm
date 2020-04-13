package cn.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.home.pojo.BaseDict;
import cn.home.pojo.Customer;
import cn.home.pojo.QueryVo;
import cn.home.utils.Page;

public interface BaseDictDao {
	//查询
	public List<BaseDict> selectByCode(String code);

	public List<Customer> selectByQueryVo(QueryVo vo);

	public Integer customerCountByQueryVo(QueryVo vo);
	
	//ajax根据id查询customer对象
	//这里传入是Integer/String这种类型，需要在dao接口方法定义的参数前面加@Param("id")才能在if标签里读取id
	public Customer selectById(@Param("id") Integer id);
	
	//保存修改
	public void saveCustomer(Customer customer);

	public void deleteCustomer(Integer id);
}
