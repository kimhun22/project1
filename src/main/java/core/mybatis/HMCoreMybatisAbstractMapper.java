package core.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import core.web.paging.data.Paging;

public interface HMCoreMybatisAbstractMapper<E> {
	public Integer insert(E param);
	public Integer update(E param);
	public Integer delete(E param);
	public E get(E param);
	public Integer countList(@Param("where") Map<String, Object> where);
	public List<E> list(@Param("where") Map<String, Object> where, @Param("paging") Paging paging);
}
