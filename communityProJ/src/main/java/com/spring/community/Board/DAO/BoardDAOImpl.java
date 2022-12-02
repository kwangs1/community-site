package com.spring.community.Board.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession session;
	
	//�Խ��� ���
	@Override
	public List<BoardVO> getList(Criteria cri) {
		return session.selectList("mapper.board.getList",cri);
	}
	//�Խ��� ���(�Ϲ�)
	@Override
	public List<BoardVO> NomalList(Criteria cri) {
		return session.selectList("mapper.board.NomalList",cri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> QnAList(Criteria cri) {
		return session.selectList("mapper.board.QnAList",cri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> AttackgetList(Criteria cri) {
		return session.selectList("mapper.board.AttackgetList",cri);
	}
	//�Խ��� ���(�ڶ�)
	@Override
	public List<BoardVO> BoastList(Criteria cri) {
		return session.selectList("mapper.board.BoastList",cri);
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
	//�ѰԽñ� ����
	@Override
	public int countList() {
		return (Integer)session.selectOne("mapper.board.countList");
	}
}
