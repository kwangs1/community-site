package com.spring.community.common.Reply.Service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.common.Reply.DAO.ReplyDAO;
import com.spring.community.common.Reply.VO.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDAO dao;

	private static Logger log = Logger.getLogger(ReplyService.class.getName());
	//���
	@Override
	public List<ReplyVO> ReplyList(int bno){
		return dao.ReplyList(bno);
	}
	
	//�ۼ�
	@Override
	public int addReply(ReplyVO reply) {
		int result = dao.addReply(reply);
		
		//��� �ۼ� �� �ش� ����� r_group �÷� ���� rno �� ���� ������Ʈ ��Ű�����ؼ� �ڵ� �ۼ�
		//���� ��۰� �������ؼ�
		if(result == 1) {
			int Group_update = dao.Group_update(reply);
			reply.setR_group(Group_update);
		}
		return result;
	}
	
	//����
	@Override
	public int ModifyReply(ReplyVO reply) {
		return dao.ModifyReply(reply);
	}
	@Override
	public ReplyVO getModifyReply(int rno) {
		log.info("��� �����˾� Service");
		return dao.getModifyReply(rno);
	}
	//����
	@Override
	public int DeleteReply(int rno) {
		return dao.DeleteReply(rno);
	}
}
