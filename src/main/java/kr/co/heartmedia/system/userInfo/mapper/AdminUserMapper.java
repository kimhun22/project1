package kr.co.heartmedia.system.userInfo.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.ThAuthorChangeHistTbVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;

@Mapper
public interface AdminUserMapper {

	/**
	 * 사용자 목록 건수 조회
	 * @param sessionVO
	 * @return
	 * @throws Exception
	 */
	public int selectAdminUserListCnt(TnUserTbExVO sessionVO) throws Exception;

	/**
	 * 사용자 목록 조회
	 * @param sessionVO
	 * @return
	 * @throws Exception
	 */
	public List<TnUserTbExVO> selectAdminUserList(TnUserTbExVO sessionVO) throws Exception;

	/**
	 * 사용자 상세 조회
	 * @param TnUserTbExVO
	 * @return
	 * @throws Exception
	 */
	public TnUserTbExVO selectAdminUserDetail(TnUserTbExVO TnUserTbExVO) throws Exception;

	/**
	 * 사용자 상세 조회
	 * @param TnUserTbExVO
	 * @return
	 * @throws Exception
	 */
	public TnUserTbExVO selectUserDetail2(TnUserTbExVO TnUserTbExVO) throws Exception;

	/**
	 * 기존 사용자의 역할 조회
	 * @param TnUserTbExVO
	 * @return
	 * @throws Exception
	 */
	public String selectOldUserRole(TnUserTbExVO TnUserTbExVO) throws Exception;

	/**
	 * 사용자 정보 수정
	 * @param TnUserTbExvo
	 * @throws Exception
	 */
	public int updateAdminUserProc(TnUserTbExVO TnUserTbExvo) throws Exception;

	/**
	 * 역할 변경 이력 기록
	 * @param TnUserTbExvo
	 * @throws Exception
	 */
	public void insertRoleChangeHist(ThAuthorChangeHistTbVO thAuthorChangeHistTbVO) throws Exception;

	/**
	 * 사용자 역할 입력
	 * @param TnUserTbExvo
	 * @throws Exception
	 */
	public void insertUserRole(TnUserTbExVO TnUserTbExvo) throws Exception;

	/**
	 * 사용자 역할 수정
	 * @param TnUserTbExvo
	 * @throws Exception
	 */
	public void updateUserRole(TnUserTbExVO TnUserTbExvo) throws Exception;

	/**
	 * 사용자 역할 삭제
	 * @param TnUserTbExvo
	 * @throws Exception
	 */
	public void deleteUserRole(TnUserTbExVO TnUserTbExvo) throws Exception;

}
