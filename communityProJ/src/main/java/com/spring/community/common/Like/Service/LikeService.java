package com.spring.community.common.Like.Service;

import com.spring.community.common.Like.VO.LikeVO;

public interface LikeService {
/* �Խñ� ���ƿ�,�Ⱦ�� */
	//���ƿ� ������
	void LikeUp(LikeVO like);
	//���ƿ� ���
	void LikeDown(LikeVO like);
	//���ƿ� ��������
	int findLike(int bno);
	//���ƿ� ����
	int getLike(int bno, int like_type);
	//�Ⱦ�� ������
	void BadUp(LikeVO like);
	//�Ⱦ�� ���
	void BadDown(LikeVO like);
	//�Ⱦ�� Ȯ��
	int findBad(int bno);



}
