package com.spring.community.Board.DAO;

import java.util.List;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.BoardAttachVO;
import com.spring.community.common.Criteria;

public interface BoardDAO {
	//���
	List<BoardVO> lists(Criteria cri);
	//���(�ڶ�)
	List<BoardVO> brag(Criteria cri);
	//���(����)
	List<BoardVO> tip(Criteria cri);
	//���(����)
	List<BoardVO> qna(Criteria cri);
	//���(�ڶ�)
	List<BoardVO> free(Criteria cri);
	//�ۼ�
	void insertSelectKey(BoardVO board);
	void insert(BoardVO board);
	//�󼼺���
	BoardVO detail(int bno);
	//��ȸ��
	int UpdateHit(int bno);
	//����
	int remove(int bno);
	//����
	int modify(BoardVO board);
	//�� �Խñ� ����
	int countList();

}
