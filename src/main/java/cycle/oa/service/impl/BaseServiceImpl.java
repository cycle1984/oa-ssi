package cycle.oa.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import cycle.oa.mapper.BaseMapper;
import cycle.oa.mapper.DocumentMapper;
import cycle.oa.mapper.MyGroupMapper;
import cycle.oa.mapper.MyResourceMapper;
import cycle.oa.mapper.RoleMapper;
import cycle.oa.mapper.SignInfoMapper;
import cycle.oa.mapper.UnitMapper;
import cycle.oa.mapper.UserMapper;
import cycle.oa.po.Page;
import cycle.oa.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	protected  BaseMapper<T> baseMapper;
	
	@Autowired
	protected MyGroupMapper myGroupMapper;
	
	@Autowired
	protected UnitMapper unitMapper;
	
	@Autowired
	protected UserMapper userMapper;
	
	@Autowired
	protected MyResourceMapper myResourceMapper;
	
	@Autowired
	protected RoleMapper roleMapper;
	
	@Autowired
	protected DocumentMapper documentMapper;
	
	@Autowired
	protected SignInfoMapper signInfoMapper;
	
	@PostConstruct//在构造方法后，初化前执行
	private void initBaseMapper() throws Exception{
		//完成以下逻辑，需要对研发本身进行命名与使用规范
				//this关键字指对象本身，这里指的是调用此方法的实现类（子类）
				System.out.println("=======this :"+this);
				System.out.println("=======父类基本信息："+this.getClass().getSuperclass());
				System.out.println("=======父类和泛型的信息："+this.getClass().getGenericSuperclass());
				
				ParameterizedType type =(ParameterizedType) this.getClass().getGenericSuperclass();
				//获取第一个参数的class
				Class clazz = (Class)type.getActualTypeArguments()[0];
				System.out.println("=======class:"+clazz);
				//转化为属性名（相关的Mapper子类的引用名）Supplier  supplierMapper
				String localField = clazz.getSimpleName().substring(0,1).toLowerCase()+clazz.getSimpleName().substring(1)+"Mapper";
				System.out.println("=======localField:"+localField);
				//getDeclaredField:可以使用于包括私有、默认、受保护、公共字段，但不包括继承的字段
				Field field=this.getClass().getSuperclass().getDeclaredField(localField);
				System.out.println("=======field:"+field);
				System.out.println("=======field对应的对象:"+field.get(this));
				Field baseField = this.getClass().getSuperclass().getDeclaredField("baseMapper");
				
				System.out.println("=======baseField:"+baseField);
				System.out.println("=======baseField对应的对象:"+baseField.get(this));	
				//field.get(this)获取当前this的field字段的值。例如：Supplier对象中，baseMapper所指向的对象为其子类型SupplierMapper对象，子类型对象已被spring实例化于容器中		
				baseField.set(this, field.get(this));		
				System.out.println("========baseField对应的对象:"+baseMapper);

	}	
	

	@Override
	public void save(T t) throws Exception {
		// TODO Auto-generated method stub
		baseMapper.insert(t);
	}

	@Override
	public void deleteByArray(Integer[] ids) throws Exception {
		// TODO Auto-generated method stub
		baseMapper.deleteByArray(ids);
	}

	@Override
	public void update(T t) throws Exception {
		// TODO Auto-generated method stub
		baseMapper.update(t);
	}

	@Override
	public T selectById(Integer id) {
		// TODO Auto-generated method stub
		return baseMapper.selectById(id);
	}

	@Override
	public T selectEntity(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.selectEntity(entity);
	}

	@Override
	public List<T> selectListByEntity(T entity) {
		// TODO Auto-generated method stub
		return baseMapper.selectListByEntity(entity);
	}

	@Override
	public Page<T> selectPageDyc(Page<T> page) {
		// TODO Auto-generated method stub
		page.setList(baseMapper.selectPageListDyc(page));
		page.setTotalRecord(baseMapper.selectPageCountDyc(page));
		return page;
	}


	@Override
	public List<T> selectAll(Map<String,Object> map) {
		return baseMapper.selectAll(map);
	}


	@Override
	public List<T> selectByIds(Integer[] ids) {
		return baseMapper.selectByIds(ids);
	}

}
