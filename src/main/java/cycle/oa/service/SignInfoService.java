package cycle.oa.service;

import java.util.List;

import cycle.oa.po.SignInfo;

public interface SignInfoService extends BaseService<SignInfo> {
	//通过docID查询附件
	public List<SignInfo> selectByDocId(Integer docId);
}
