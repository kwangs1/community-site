package com.spring.community.Board.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.community.Board.VO.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession session;
	
	//�Խ��� ���
	@Override
	public List<BoardVO> getList() {
		return session.selectList("mapper.board.getList");
	}
	//�Խñ� �ۼ�
	@Override
	public void register(BoardVO board) {
		session.insert("mapper.board.register",board);
	}
	//���
	@Override
	public BoardVO detail(int bno) {
		return session.selectOne("mapper.board.detail",bno);
	}
	//��ȸ��
	@Override
	public int UpdateHit(int bno) {
		return session.update("mapper.board.UpdateHit",bno);
	}
	//����
	@Override
	public int remove(int bno) {
		return session.delete("mapper.board.remove",bno);
	}
	//����
	@Override
	public int modify(BoardVO board) {
		return session.update("mapper.board.modify",board); 
	}
}
