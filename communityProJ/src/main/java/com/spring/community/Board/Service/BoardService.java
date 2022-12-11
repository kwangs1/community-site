package com.spring.community.Board.Service;

import java.util.List;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.BoardAttachVO;
import com.spring.community.common.Criteria;

public interface BoardService {
	//���
	List<BoardVO> lists(Criteria cri);
	//���(�ڶ�)
	List<BoardVO> brag(Criteria cri);
	//���(�Ƿ�)
	List<BoardVO> tip(Criteria cri);
	//���(����)
	List<BoardVO> qna(Criteria cri);
	//���(����)
	List<BoardVO> free(Criteria cri);
	//�ۼ�
	void register(BoardVO board);
	//�󼼺���
	BoardVO detail(int bno);
	//��ȸ��
	int UpdateHit(int bno);
	//����
	boolean remove(int bno);
	//����
	boolean modify(BoardVO board);
	//�� �Խñ� ����
	int countList();
	List<BoardAttachVO> getAttachList(int bno);
	void removeAttach(int bno);

}
