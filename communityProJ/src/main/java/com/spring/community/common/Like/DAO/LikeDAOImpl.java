package com.spring.community.common.Like.DAO;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.community.common.Like.VO.LikeVO;

@Repository
public class LikeDAOImpl implements LikeDAO{
	private static Logger log = Logger.getLogger(LikeDAO.class.getName());
	
	@Autowired
	private SqlSession session;
/* �Խñ� ���ƿ�,�Ⱦ�� */	
	//���ƿ� ������
	@Override
	public void LikeUp(LikeVO like) {
		session.insert("mapper.Like.LikeUp", like);
	}	
	//���ƿ� ���
	@Override
	public void LikeDown(LikeVO like) {
		session.delete("mapper.Like.LikeDown", like);
	}	
	//���ƿ� Ȯ��
	@Override
	public int findLike(int bno) {
		log.info("findLike....Check.."+bno);
		return session.selectOne("mapper.Like.findLike",bno);
	}
	//���ƿ� ����
	@Override
	public int getLike(Map<String,Object> data) {
		log.info("getLike...check..."+data);
		return session.selectOne("mapper.Like.getLike",data);
	}
	
	//�Ⱦ�� ������
	@Override
	public void BadUp(LikeVO like) {
		session.insert("mapper.Like.BadUp", like);
	}	
	//�Ⱦ�� ���
	@Override
	public void BadDown(LikeVO like) {
		session.delete("mapper.Like.BadDown", like);
	}	
	//�Ⱦ�� Ȯ��
	@Override
	public int findBad(int bno) {
		log.info("findBad....Check.."+bno);
		return session.selectOne("mapper.Like.findBad",bno);
	}
	
}
