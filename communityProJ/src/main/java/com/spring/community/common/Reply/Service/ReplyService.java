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
	//����
	int DeleteReply(int rno);

}
