package com.spring.community.common.Like.Service;

import com.spring.community.common.Like.VO.LikeVO;

public interface LikeService {
	//���ƿ� ������
	void LikeUp(LikeVO like);
	//���ƿ� ���
	void LikeDown(LikeVO like);
	//���ƿ� ��������
	int findLike(int bno);
	//���ƿ� ����
	int getLike(int bno, int like_type);

}
