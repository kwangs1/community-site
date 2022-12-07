package com.spring.community.Board.Service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.Board.DAO.BoardDAO;
import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.Criteria;

@Service
public class BoardServiceImpl implements BoardService{
	private static Logger log = Logger.getLogger(BoardService.class.getName());
	@Autowired
	private BoardDAO dao;
	
	//�Խ��� ���
	@Override
	public List<BoardVO> lists(Criteria cri) {
		return dao.lists(cri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> free(Criteria cri) {
		return dao.free(cri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> qna(Criteria cri) {
		return dao.qna(cri);
	}
	//�Խ��� ���(����)
	@Override
	public List<BoardVO> tip(Criteria cri) {
		return dao.tip(cri);
	}
	//�Խ��� ���(�ڶ�)
	@Override
	public List<BoardVO> brag(Criteria cri) {
		return dao.brag(cri);
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
	//�� �Խñ� ����
	@Override
	public int countList() {
		return dao.countList();
	}
}
