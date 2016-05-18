package cycle.oa.service.impl;

import org.springframework.stereotype.Service;

import cycle.oa.po.News;
import cycle.oa.service.NewsService;

@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {

}
