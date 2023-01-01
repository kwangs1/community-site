package com.spring.community.common.Scrap.DAO;

import java.util.List;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.Criteria;
import com.spring.community.common.Scrap.VO.ScrapVO;

public interface ScrapDAO {
	//��ũ�� ����Ʈ
	List<ScrapVO> ScrapList(ScrapVO scrap);
	List<BoardVO> boardList(Criteria cri);
	//��ũ�� �Ǿ��ִ� ������?
	boolean findScrap(ScrapVO scrap);
	//��ũ�� �߰�
	void addScrap(ScrapVO scrap);
	//��ũ�� ����
	void removeScrap(int sno);
	int getScrapTotal(String id);
}
