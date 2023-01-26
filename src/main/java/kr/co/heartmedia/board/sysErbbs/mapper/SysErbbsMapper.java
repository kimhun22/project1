package kr.co.heartmedia.board.sysErbbs.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.extend.TnBbsTbExVO;



@Mapper
public interface SysErbbsMapper {

    int selectSysErbbsListCnt(TnBbsTbExVO tnBbsTbExVO) throws Exception;

    List<TnBbsTbExVO> selectSysErbbsList(TnBbsTbExVO tnBbsTbExVO) throws Exception;

    TnBbsTbExVO selectSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception;

    void insertSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception;

    void updateSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception;

    void deleteSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception;


}
