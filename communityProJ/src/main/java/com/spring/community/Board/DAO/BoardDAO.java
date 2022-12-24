package com.spring.community.Board.DAO;

import java.util.List;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.SearchCriteria;

public interface BoardDAO {
	//���
	List<BoardVO> lists(SearchCriteria scri);
	//���(�ڶ�)
	List<BoardVO> brag(SearchCriteria scri);
	//���(����)
	List<BoardVO> tip(SearchCriteria scri);
	//���(����)
	List<BoardVO> qna(SearchCriteria scri);
	//���(�ڶ�)
	List<BoardVO> free(SearchCriteria scri);
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
	//��� ����
	void reply_count(int bno);
	//�Խñ� ��� ���ƿ� ����
	void like_count(int bno);

}
