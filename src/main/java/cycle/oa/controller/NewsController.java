package cycle.oa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.po.News;
import cycle.oa.po.Page;
import cycle.oa.po.Unit;
import cycle.oa.po.User;

/**
 * 信息资讯控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController{

	/**
	 * 获得所有信息资讯
	 * @param page
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/grid.do")
	public Object grid(Page<News> page , News news){
		if(news.getTitle()!=null){//如果name属性不为空， 模糊查询
			news.setTitle("%"+news.getTitle()+"%");
		}
		page.setParamEntity(news);//将后台数据传到page模型中
		
		Page<News> p = newsService.selectPageDyc(page);
		return p.getPageMap();
	}
	
	@ResponseBody
	@RequestMapping("/myGrid.do")
	public Object myGrid(Page<News> page , News news){
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		User user = (User) subject.getPrincipal();
		if(!user.isAdmin()){//不是管理员
			Unit unit = user.getUnit();
			if(unit!=null){
				news.setUnit(unit);
			}
		}
		if(news.getTitle()!=null){//如果name属性不为空， 模糊查询
			news.setTitle("%"+news.getTitle()+"%");
		}
		page.setParamEntity(news);//将后台数据传到page模型中
		
		Page<News> p = newsService.selectPageDyc(page);
		return p.getPageMap();
	}
	
	public Object save(News news){
		
		return null;
	}
}
