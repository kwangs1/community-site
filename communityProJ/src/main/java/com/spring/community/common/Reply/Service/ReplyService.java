package com.spring.community.common.Reply.Service;

import java.util.List;

import com.spring.community.common.Reply.VO.ReplyVO;

public interface ReplyService {
	//���
	List<ReplyVO> ReplyList(int bno);
	//�ۼ�
	int addReply(ReplyVO reply);
	//����
	int ModifyReply(ReplyVO reply);
	ReplyVO getModifyReply(int rno);
	//����
	int DeleteReply(ReplyVO reply);
	//���� �ۼ�
	int addRe_Reply(ReplyVO reply);
}
