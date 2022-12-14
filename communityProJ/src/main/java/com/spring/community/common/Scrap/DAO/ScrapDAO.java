package com.spring.community.common.Scrap.DAO;

import java.util.List;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.Criteria;
import com.spring.community.common.Scrap.VO.ScrapVO;

public interface ScrapDAO {
	//스크랩 리스트
	List<ScrapVO> ScrapList(ScrapVO scrap);
	List<BoardVO> boardList(Criteria cri);
	//스크랩 되어있는 글인지?
	boolean findScrap(ScrapVO scrap);
	//스크랩 추가
	void addScrap(ScrapVO scrap);
	//스크랩 삭제
	void removeScrap(int sno);
	int getScrapTotal(String id);
}
