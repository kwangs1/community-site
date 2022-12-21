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
	public int DeleteReply(ReplyVO reply) {
		
		//��ۿ� �޸� ���� ������ ����� ī��Ʈ
		int Count_ReReply = dao.Count_ReReply(reply);
		
		int result = 0;
			if(Count_ReReply == 0) {
				result = dao.DeleteReply(reply);
			}else {
				//���� ��ۿ� ����� �޷����� ��쿡 ��� ������ (������ ���)�̶�� ���� �ִ´�
				result = dao.Reply_Content(reply);
			}
		
		return result;
		
//		return  dao.DeleteReply(rno);
	}
	
	//���� �ۼ�
	@Override
	public int addRe_Reply(ReplyVO reply){
		log.info("���� �ۼ� ����S");
		return dao.addRe_Reply(reply);
	}

}
