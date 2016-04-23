package cycle.oa.mapper;

import java.util.List;

import cycle.oa.po.SignInfo;

public interface SignInfoMapper extends BaseMapper<SignInfo>{
	//通过docID查询附件
	public List<SignInfo> selectByDocId(Integer docId);
}
