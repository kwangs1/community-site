package com.spring.community.common.Reply.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.community.common.Reply.VO.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	@Autowired
	private SqlSession session;
	
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
	
	//����
	@Override
	public int DeleteReply(int rno) {
		return session.delete("mapper.reply.DeleteReply",rno);
	}
	
	//�� �ۼ��� ��� ��ȣ�� �׷��ȣ ������ ������Ʈ
	@Override
	public int Group_update(ReplyVO reply) {
		return session.update("mapper.reply.Group_update",reply);
	}
}
