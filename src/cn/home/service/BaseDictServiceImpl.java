package cn.home.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.home.mapper.BaseDictDao;
import cn.home.pojo.BaseDict;
import cn.home.pojo.Customer;
import cn.home.pojo.QueryVo;
import cn.home.utils.Page;


@Service
public class BaseDictServiceImpl {
	@Resource
	private BaseDictDao baseDictDao;
	
	public List<BaseDict> selectByCode(String code){
		return baseDictDao.selectByCode(code);
	}

	public Page<Customer> selectByQueryVo(QueryVo vo) {
		Page<Customer> page = new Page<Customer>();
		if (vo.getPage()!=null) {
			vo.setStartRow((vo.getPage()-1)*vo.getSize());
			page.setPage(vo.getPage());
		}
		//排除" "的字符输入操作
		if (vo.getCustIndustry()==null || "".equals(vo.getCustIndustry().trim())) {
			vo.setCustIndustry(null);
		}
		if (vo.getCustLevel()==null || "".equals(vo.getCustLevel().trim())) {
			vo.setCustLevel(null);
		}
		if (vo.getCustName()==null || "".equals(vo.getCustName().trim())) {
			vo.setCustName(null);
		}
		if (vo.getCustSource()==null || "".equals(vo.getCustSource().trim())) {
			vo.setCustSource(null);
		}
		
		
		List<Customer> customers = baseDictDao.selectByQueryVo(vo);
		Integer count = baseDictDao.customerCountByQueryVo(vo);
		
		page.setSize(vo.getSize());
		page.setRows(customers);
		page.setTotal(count);
		return page;
	}
	
	public Customer selectById(Integer id) {
		return baseDictDao.selectById(id);
	}
	
	public void saveCustomer(Customer customer) {
		baseDictDao.saveCustomer(customer);
	}

	public void deleteCustomer(Integer id) {
		baseDictDao.deleteCustomer(id);
	}
}
