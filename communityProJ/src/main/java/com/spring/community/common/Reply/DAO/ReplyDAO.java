package com.spring.community.common.Reply.DAO;

import java.util.List;

import com.spring.community.common.Reply.VO.ReplyVO;

public interface ReplyDAO {
	//���
	List<ReplyVO> ReplyList(int bno);
	//�ۼ�
	int addReply(ReplyVO reply);
	//����
	int ModifyReply(ReplyVO reply);
	//����
	int DeleteReply(int rno);
	//�� �ۼ��� ��� ��ȣ�� �׷��ȣ ������ ������Ʈ
	int Group_update(ReplyVO reply);

}
