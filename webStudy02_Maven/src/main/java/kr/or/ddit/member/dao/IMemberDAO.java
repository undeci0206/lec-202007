package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원관리(CRUD)를 위한 Persistence Layer
 *
 */
public interface IMemberDAO {
	/**
	 * 회원 등록
	 * @param member
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int insertMember(MemberVO member);
	/**
	 * 회원목록 조회
	 * @return 존재하지 않을때, size == 0
	 */
	public List<MemberVO> selectMemberList();
	/**
	 * 회원 상세 조회
	 * @param mem_id
	 * @return 존재하지 않을때 null 반환
	 */
	public MemberVO selectMember(String mem_id);
	/**
	 * 회원 정보 수정(자기 정보 수정)
	 * @param member
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int updateMember(MemberVO member);
	/**
	 * 회원 탈퇴 처리(???)
	 * @param mem_id
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	//pass까지 파라미터 넘기려면 이전에 암호화된 상태여야 함
	public int deleteMember(String mem_id);
}



