package cycle.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cycle.oa.po.MyFile;
import cycle.oa.service.MyFileService;

@Service("myFileService")
public class MyFileServiceImpl extends BaseServiceImpl<MyFile> implements MyFileService {

	//通过docID查询附件
	@Override
	public List<MyFile> selectByDocId(Integer docId) {
		// TODO Auto-generated method stub
		return myFileMapper.selectByDocId(docId);
	}


}
