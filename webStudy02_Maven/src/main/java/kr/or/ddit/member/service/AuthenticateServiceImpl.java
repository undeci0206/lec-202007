package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService {
	IMemberDAO dao = new MemberDAOImpl();
	
	@Override
	public Object authenticate(MemberVO paramVO) {
		Object result = null;
		MemberVO member = dao.selectMember(paramVO.getMem_id());
		if(member!=null) {
			String input = paramVO.getMem_pass();
			String encoded = SecurityUtils.encyptSha512(input);
			String saved = member.getMem_pass();
			System.out.println(input);
			if(saved.equals(encoded)) {
				result = member;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			result=ServiceResult.NOTEXIST;
		}
		return result;
	}

}
