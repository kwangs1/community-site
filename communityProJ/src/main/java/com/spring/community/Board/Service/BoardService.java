package com.spring.community.Board.Service;

import java.util.List;

import com.spring.community.Board.VO.BoardVO;

public interface BoardService {
	//���
	List<BoardVO> getList();
	//�ۼ�
	void register(BoardVO board);
	//�󼼺���
	BoardVO detail(int bno);
	//��ȸ��
	int UpdateHit(int bno);
	//����
	int remove(int bno);
	//����
	int modify(BoardVO board);

}
