package cycle.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cycle.oa.po.SignInfo;
import cycle.oa.service.SignInfoService;

@Service("signInfoService")
public class SignInfoServiceImpl extends BaseServiceImpl<SignInfo> implements SignInfoService {

	@Override
	public List<SignInfo> selectByDocId(Integer docId) {
		// TODO Auto-generated method stub
		return signInfoMapper.selectByDocId(docId);
	}


}
