package com.doo.batch.mybatis;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public Map<String, Object> select(String queryId, Object param) {
		return sqlSession.selectOne(queryId, param);
	}
	
	public List<Map<String, Object>> selectList(String queryId, Object param) {
		return sqlSession.selectList(queryId, param);
	}
	
	public Integer insert(String queryId, Object param) {
		return sqlSession.insert(queryId, param);
	}
	
	public Integer delete(String queryId, Object param) {
		return sqlSession.delete(queryId, param);
	}
}
