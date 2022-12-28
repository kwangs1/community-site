package com.spring.community.common.Like.DAO;

import java.util.Map;

import com.spring.community.common.Like.VO.LikeVO;
import com.spring.community.common.Like.VO.ReplyLikeVO;

public interface LikeDAO {
/* �Խñ� ���ƿ�,�Ⱦ�� */
	//���ƿ䴩����
	void LikeUp(LikeVO like);
	//���ƿ� ���
	void LikeDown(LikeVO like);
	//���ƿ� Ȯ��
	int findLike(int bno);
	//���ƿ� ����
	int getLike(Map<String, Object> data);
	//�Ⱦ�� ������
	void BadUp(LikeVO like);
	//�Ⱦ�� ���
	void BadDown(LikeVO like);
	//�Ⱦ�� Ȯ��
	int findBad(int bno);

/* ��� ���ƿ�,�Ⱦ�� */
	//���ƿ� ������
	void ReplyLikeUp(ReplyLikeVO replyLike);
	//���ƿ� ���
	void ReplyLikeDown(ReplyLikeVO replyLike);
	//���ƿ� Ȯ��
	int findReLike(int rno);
	//�Ⱦ�� ������
	void ReplyBadUp(ReplyLikeVO replyLike);
	//�Ⱦ�� ���
	void ReplyBadDown(ReplyLikeVO replyLike);
	//�Ⱦ�� Ȯ��
	int findReBad(int rno);

}
