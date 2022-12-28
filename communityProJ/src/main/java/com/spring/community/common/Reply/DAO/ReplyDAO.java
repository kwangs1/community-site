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
	ReplyVO getModifyReply(int rno);
	//����
	int DeleteReply(ReplyVO reply);
	
	//��ۿ� �޸� ���� ���� ī��Ʈ
	int Count_ReReply(ReplyVO reply);
	//��� ������ ���� �޷������� (������ ���)�̶�� ����
	int Reply_Content(ReplyVO reply);
	
	//�� �ۼ��� ��� ��ȣ�� �׷��ȣ ������ ������Ʈ
	int Group_update(ReplyVO reply);
	//���� �ۼ�
	int addRe_Reply(ReplyVO reply);
	//��ۿ� �޸� ���ƿ� �߰� �� ����
	//�߰�
	int ReplyLikeUp(int rno);
	//����
	int ReplyLikeDown(int rno);

}
