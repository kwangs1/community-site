package com.spring.community.Board.Service;

import java.util.List;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.BoardAttachVO;
import com.spring.community.common.SearchCriteria;

public interface BoardService {
	//���
	List<BoardVO> lists(SearchCriteria scri);
	//���(�ڶ�)
	List<BoardVO> brag(SearchCriteria scri);
	//���(�Ƿ�)
	List<BoardVO> tip(SearchCriteria scri);
	//���(����)
	List<BoardVO> qna(SearchCriteria scri);
	//���(����)
	List<BoardVO> free(SearchCriteria scri);
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
	//��� ����
	void reply_count(int bno);
	//���ƿ� ����
	void like_count(int bno);
	//�̹��� ���� ����Ʈ
	List<BoardAttachVO> getAttachList(int bno);
	//�̹��� ����
	void removeAttach(int bno);

}
