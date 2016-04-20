package cycle.oa.service;

import java.util.List;

import cycle.oa.po.MyFile;

public interface MyFileService extends BaseService<MyFile> {

	//通过docID查询附件
	public List<MyFile> selectByDocId(Integer docId);
}
