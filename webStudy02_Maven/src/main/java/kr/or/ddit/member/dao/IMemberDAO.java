package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원관리를 위한 Persistence Layer
 */
public interface IMemberDAO {
	public MemberVO selectMember(String mem_id);
	//주석
}
