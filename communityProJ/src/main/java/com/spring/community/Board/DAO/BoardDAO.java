package com.spring.community.Board.DAO;

import java.util.List;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.Criteria;

public interface BoardDAO {
	//���
	List<BoardVO> getList(Criteria cri);
	//���(�ڶ�)
	List<BoardVO> BoastList(Criteria cri);
	//���(����)
	List<BoardVO> AttackgetList(Criteria cri);
	//���(����)
	List<BoardVO> QnAList(Criteria cri);
	//���(�Ϲ�)
	List<BoardVO> NomalList(Criteria cri);
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
	//�� �Խñ� ����
	int countList();
}
