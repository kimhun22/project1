package kr.co.heartmedia.board.sysErbbs.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

import kr.co.heartmedia.vo.TnAtchFileTbVO;
import kr.co.heartmedia.vo.extend.TnBbsTbExVO;




public interface SysErbbsService {

    void selectSysErbbsList(ModelMap model, TnBbsTbExVO tnBbsTbExVO) throws Exception;

    TnBbsTbExVO selectSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception;

    void insertSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception;

    void updateSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception;

    void deleteSysErbbs(TnBbsTbExVO tnBbsTbExVO) throws Exception;

    Map<String, Object> deleteSysErbbsAtchFile(TnAtchFileTbVO tnAtchFileTbVO, ModelMap modelMap) throws Exception;

}
