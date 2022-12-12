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
	
	//게시판 목록
	@Override
	public List<BoardVO> lists(Criteria cri) {
		return dao.lists(cri);
	}
	//게시판 목록(자유)
	@Override
	public List<BoardVO> free(Criteria cri) {
		return dao.free(cri);
	}
	//게시판 목록(질문)
	@Override
	public List<BoardVO> qna(Criteria cri) {
		return dao.qna(cri);
	}
	//게시판 목록(공략)
	@Override
	public List<BoardVO> tip(Criteria cri) {
		return dao.tip(cri);
	}
	//게시판 목록(자랑)
	@Override
	public List<BoardVO> brag(Criteria cri) {
		return dao.brag(cri);
	}
	//게시판 작성
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
	//목록
	@Override
	public BoardVO detail(int bno) {
		log.info("detail...." + bno);
		return dao.detail(bno);
	}
	//조회수
	@Override
	public int UpdateHit(int bno) {
		return dao.UpdateHit(bno);
	}
	//삭제
	@Override
	public boolean remove(int bno) {
		log.info("remove..."+bno);
		
		boardAttach.deleteImg(bno);
		return dao.remove(bno) == 1;
	}
	//수정
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
	//총 게시글 갯수
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
