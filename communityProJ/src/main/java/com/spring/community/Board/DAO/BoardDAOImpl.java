package com.spring.community.Board.DAO;

import java.util.List;
import java.util.Map;

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
	public List<BoardVO> lists(Criteria cri) {
		return session.selectList("mapper.board.lists",cri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> free(Criteria cri) {
		return session.selectList("mapper.board.free",cri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> qna(Criteria cri) {
		return session.selectList("mapper.board.qna",cri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> tip(Criteria cri) {
		return session.selectList("mapper.board.tip",cri);
	}
	//�Խ��� ���(�ڶ�)
	@Override
	public List<BoardVO> brag(Criteria cri) {
		return session.selectList("mapper.board.brag",cri);
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
		return session.selectOne("mapper.board.countList");
	}
}
