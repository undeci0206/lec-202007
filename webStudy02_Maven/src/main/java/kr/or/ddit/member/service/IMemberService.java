package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리를 위한 Business Logic Layer
 *
 */
public interface IMemberService {
	/**
	 * 회원등록
	 * @param member
	 * @return PKDUPLICATED, OK, FAILED
	 */
	//1. ID중복 고려 2.성공 3.실패 3개의 case식별 -> enum 사용
	public ServiceResult registMember(MemberVO member);
	/**
	 * 회원 목록 조회(추후 검색/페이징 기능 추가)
	 * @return
	 */
	public List<MemberVO> retrieveMemberList();
	/**
	 * 회원 상세 조회
	 * @param mem_id
	 * @return 존재하지 않는다면, custom exception 생성(발생할 수 있는 예외 적용해서 써먹자)
	 */
	public MemberVO retrieveMember(String mem_id);
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 존재하지 않는다면, custom exception 생성, INVALIDPASSWORD, OK, FAILED
	 */
	//1.A라는 회원이 없음 2.본인 맞는지 신원 확인(인증), 인증 실패 3.인증 성공 4.정보 수정 성공/실패
	public ServiceResult modifyMember(MemberVO member);
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return 존재하지 않는다면, custom exception 생성, INVALIDPASSWORD, OK, FAILED
	 */
	//1.탈퇴할 회원 있는지 확인 2.본인 맞는지 신원 확인(인증), 인증 실패 3.인증 성공 4.탈퇴 성공/실패
	public ServiceResult removeMember(MemberVO member);
}
