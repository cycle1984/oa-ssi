package cycle.oa.mapper;

import java.util.List;

import cycle.oa.po.MyFile;

public interface MyFileMapper extends BaseMapper<MyFile>{
	
	//通过docID查询附件
	public List<MyFile> selectByDocId(Integer docId);
}
