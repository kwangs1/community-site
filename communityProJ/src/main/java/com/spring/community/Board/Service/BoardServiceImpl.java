package com.spring.community.Board.Service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.community.Board.DAO.BoardAttachDAO;
import com.spring.community.Board.DAO.BoardDAO;
import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.BoardAttachVO;
import com.spring.community.common.Criteria;

@Service
public class BoardServiceImpl implements BoardService{
	private static Logger log = Logger.getLogger(BoardService.class.getName());
	@Autowired
	private BoardDAO dao;
	@Autowired
	private BoardAttachDAO boardAttach;
	
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
	@Transactional
	@Override
	public void register(BoardVO board) {
		log.info("register....." + board);
		dao.insertSelectKey(board);
		
		if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}

		board.getAttachList().forEach(attach -> {

			attach.setBno(board.getBno());
			boardAttach.board_image(attach);
		});
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
	public boolean remove(int bno) {
		log.info("remove..."+bno);
		
		boardAttach.deleteImg(bno);
		return dao.remove(bno) == 1;
	}
	//����
	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify.."+board);
		boardAttach.deleteImg(board.getBno());
		
		boolean modifyResult = dao.modify(board) == 1;
		
		if(modifyResult && board.getAttachList().size() >0) {
			
			board.getAttachList().forEach(attach ->{
				
				attach.setBno(board.getBno());
				boardAttach.board_image(attach);
			});		
		}
		return modifyResult;
	}
	//�� �Խñ� ����
	@Override
	public int countList() {
		return dao.countList();
	}
	
	@Override
	public List<BoardAttachVO> getAttachList(int bno){
		log.info("get Attach list by bno" + bno);
		
		return boardAttach.getAttachList(bno);
	}
	
	@Override
	public void removeAttach(int bno) {

		log.info("remove all attach files");

		boardAttach.deleteImg(bno);
	}
}
