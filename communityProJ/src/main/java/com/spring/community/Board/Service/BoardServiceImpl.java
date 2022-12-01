package com.spring.community.Board.Service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.Board.DAO.BoardDAO;
import com.spring.community.Board.VO.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	private static Logger log = Logger.getLogger(BoardService.class.getName());
	@Autowired
	private BoardDAO dao;
	
	//�Խ��� ���
	@Override
	public List<BoardVO> getList() {
		log.info("getList......");
		return dao.getList();
	}
	//�Խ��� �ۼ�
	@Override
	public void register(BoardVO board) {
		log.info("register....." + board);
		dao.register(board);
	}
	//���
	@Override
	public BoardVO detail(int bno) {
		log.info("detail...." + bno);
		return dao.detail(bno);
	}
	//��ȸ��
	@Override
	public int UpdateHit(int bno) {
		return dao.UpdateHit(bno);
	}
	//����
	@Override
	public int remove(int bno) {
		return dao.remove(bno);
	}
	//����
	@Override
	public int modify(BoardVO board) {
		log.info("modify.."+board);
		return dao.modify(board);
	}
}
