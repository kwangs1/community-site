package com.spring.community.common.Reply.DAO;

import java.util.List;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.community.common.Reply.VO.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	@Autowired
	private SqlSession session;
	
	private static Logger log = Logger.getLogger(ReplyDAO.class.getName());
	//���
	@Override
	public List<ReplyVO> ReplyList(int bno){
		return session.selectList("mapper.reply.ReplyList",bno);
	}
	
	//�ۼ�
	@Override
	public int addReply(ReplyVO reply) {
		return session.insert("mapper.reply.addReply",reply);
	}
	
	//����
	@Override
	public int ModifyReply(ReplyVO reply) {
		return session.update("mapper.reply.ModifyReply",reply);
	}
	@Override
	public ReplyVO getModifyReply(int rno) {
		log.info("��� �����˾�â dao");
		return session.selectOne("mapper.reply.getModifyReply",rno);
	}
	//����
	@Override
	public int DeleteReply(ReplyVO reply) {
		return session.delete("mapper.reply.DeleteReply",reply);
	}
	
	//������ ��ۿ� ���� ī��Ʈ
	@Override
	public int Count_ReReply(ReplyVO reply) {
		return session.selectOne("mapper.reply.Count_ReReply",reply);
	}
	//������ �޸� ��� ������ ���������� �ƴ� (������ ���)�̶�� ǥ��
	@Override
	public int Reply_Content(ReplyVO reply) {
		return session.update("mapper.reply.Reply_Content",reply);
	}
	
	//�� �ۼ��� ��� ��ȣ�� �׷��ȣ ������ ������Ʈ
	@Override
	public int Group_update(ReplyVO reply) {
		return session.update("mapper.reply.Group_update",reply);
	}
	
	//���� �ۼ�
	@Override
	public int addRe_Reply(ReplyVO reply) {
		return session.insert("mapper.reply.addRe_Reply",reply);
	}
	
	//��ۿ� �޸� ���ƿ� �߰� �� ����
	//�߰�
	@Override
	public int ReplyLikeUp(int rno) {
		return session.update("mapper.reply.ReplyLikeUp",rno);
	}
	//����
	@Override
	public int ReplyLikeDown(int rno) {
		return session.update("mapper.reply.ReplyLikeDown",rno);
	}
	
	//��ۿ� �޸� �Ⱦ�� �߰� �� ����
	//�߰�
	@Override
	public int ReplyBadUp(int rno) {
		return session.update("mapper.reply.ReplyBadUp",rno);
	}
	//����
	@Override
	public int ReplyBadDown(int rno) {
		return session.update("mapper.reply.ReplyBadDown",rno);
	}
}
