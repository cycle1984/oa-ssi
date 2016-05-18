package cycle.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cycle.oa.po.News;
import cycle.oa.po.Page;

/**
 * 信息资讯控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController{

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
}
