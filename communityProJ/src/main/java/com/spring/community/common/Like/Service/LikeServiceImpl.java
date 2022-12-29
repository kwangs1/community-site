package com.spring.community.common.Like.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.common.Like.DAO.LikeDAO;
import com.spring.community.common.Like.VO.LikeVO;
import com.spring.community.common.Like.VO.ReplyLikeVO;

@Service
public class LikeServiceImpl implements LikeService{
	private static Logger log = Logger.getLogger(LikeService.class.getName());
	
	@Autowired
	private LikeDAO dao;

/* �Խñ� ���ƿ�,�Ⱦ�� */	
	//���ƿ� ������
	@Override
	public void LikeUp(LikeVO like) {
		log.info("���ƿ� ����....." + like);
		dao.LikeUp(like);
	}	
	//���ƿ� ���
	@Override
	public void LikeDown(LikeVO like) {
		log.info("���ƿ� ���....." + like);
		dao.LikeDown(like);			
	}	
	//���ƿ� Ȯ��
	@Override
	public int findLike(int bno) {
		log.info("findLike...Check..."+bno);
		return dao.findLike(bno);
	}	
	//���ƿ� ����
	@Override
	public int getLike(int bno, int like_type) {
		log.info("getLike...check..."+bno+"\\\\"+like_type);
		Map<String,Object>map = new HashMap<>();
		map.put("bno", bno);
		map.put("like_type", like_type);
		
		return dao.getLike(map);
	}

	//�Ⱦ�� ������
	@Override
	public void BadUp(LikeVO like) {
		log.info("�Ⱦ�� ����....." + like);
		dao.BadUp(like);
	}
	//�Ⱦ�� ���
	@Override
	public void BadDown(LikeVO like) {
		log.info("�Ⱦ�� ���....." + like);
		dao.BadDown(like);			
	}
	//�Ⱦ�� Ȯ��
	@Override
	public int findBad(int bno) {
		log.info("findBad...Check..."+bno);
		return dao.findBad(bno);
	}
	//�Ⱦ�� ����
	@Override
	public int getBad(int bno, int bad_type) {
		log.info("getLike...check..."+bno+"\\\\"+bad_type);
		Map<String,Object>map = new HashMap<>();
		map.put("bno", bno);
		map.put("bad_type", bad_type);
		
		return dao.getBad(map);
	}

/* ��� ���ƿ�,�Ⱦ�� */
	//���ƿ� ������
	@Override
	public void ReplyLikeUp(ReplyLikeVO replyLike) {
		dao.ReplyLikeUp(replyLike);
	}
	//���ƿ� ���
	@Override
	public void ReplyLikeDown(ReplyLikeVO replyLike) {
		dao.ReplyLikeDown(replyLike);
	}
	//���ƿ� Ȯ��
	@Override
	public int findReLike(int rno) {
		return dao.findReLike(rno);
	}	
	
	//�Ⱦ�� ������
	@Override
	public void ReplyBadUp(ReplyLikeVO replyLike) {
		dao.ReplyBadUp(replyLike);
	}
	//�Ⱦ�� ���
	@Override
	public void ReplyBadDown(ReplyLikeVO replyLike) {
		dao.ReplyBadDown(replyLike);
	}
	//�Ⱦ�� Ȯ��
	@Override
	public int findReBad(int rno) {
		return dao.findReBad(rno);
	}	
	
	

}
