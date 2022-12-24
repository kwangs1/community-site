package com.spring.community.Board.DAO;

import java.util.List;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO{
	private static Logger log = Logger.getLogger(BoardDAO.class.getName());
	@Autowired
	private SqlSession session;
	
	//�Խ��� ���
	@Override
	public List<BoardVO> lists(SearchCriteria scri) {
		return session.selectList("mapper.board.lists",scri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> free(SearchCriteria scri) {
		return session.selectList("mapper.board.free",scri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> qna(SearchCriteria scri) {
		return session.selectList("mapper.board.qna",scri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> tip(SearchCriteria scri) {
		return session.selectList("mapper.board.tip",scri);
	}
	//�Խ��� ���(�ڶ�)
	@Override
	public List<BoardVO> brag(SearchCriteria scri) {
		return session.selectList("mapper.board.brag",scri);
	}
	//�Խñ� �ۼ�
	@Override
	public void insertSelectKey(BoardVO board) {
		session.insert("mapper.board.insertSelectKey",board);
	}
	@Override
	public void insert(BoardVO board) {
		session.insert("mapper.board.insert",board);
	}
	//�󼼺���
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
	//��� ����
	@Override
	public void reply_count(int bno) {
		 session.update("mapper.board.reply_count",bno);
	}
	//�Խñ� ��� ���ƿ� ���� 
	@Override
	public void like_count(int bno) {
		log.info("���ƿ� ���� ī��Ʈ�Ѥ�....");
		 session.update("mapper.board.like_count",bno);
	}

}
