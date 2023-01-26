package core.mybatis;

import java.util.List;
import java.util.Map;

import core.web.paging.data.Paging;

public interface HMCoreMybatisAbstractService<E> {
	public Integer insert(E param);
	public Integer update(E param);
	public Integer delete(E param);
	public E get(E param);
	public Integer countList(Map<String, Object> where);
	public List<E> list(Map<String, Object> where, Paging paging);
}
