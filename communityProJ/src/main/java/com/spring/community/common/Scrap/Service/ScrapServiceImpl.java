package com.spring.community.common.Scrap.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.Scrap.Controller.ScrapController;
import com.spring.community.common.Scrap.DAO.ScrapDAO;
import com.spring.community.common.Scrap.VO.ScrapVO;

@Service
public class ScrapServiceImpl implements ScrapService{
	private static Logger log = Logger.getLogger(ScrapService.class.getName());
	
	@Autowired
	private ScrapDAO dao;
	
	//��ũ�� ����Ʈ
	@Override
	public Map<String,List> ScrapList(ScrapVO scrap){
		Map<String,List> map = new HashMap<String,List>();
		List<ScrapVO> ScrapList = dao.ScrapList(scrap);
		
		//���� ��ũ�� �� �Խñ��� �������
		if(ScrapList.size() == 0) {
			return null;
		}
		//��ũ�� �� �Խù� ���� �������� ����.
		List<BoardVO> boardList = dao.boardList(ScrapList);
		
		//��� ������ map ��� ����
		map.put("ScrapList", ScrapList);
		map.put("boardList", boardList);
		return map;
	}
	
	//��ũ���� �Ͽ��� �Խñ��ΰ�?
	@Override
	public boolean findScrap(ScrapVO scrap) {
		return dao.findScrap(scrap);
	}
	
	//��ũ�� �߰�
	@Override
	public void addScrap(ScrapVO scrap) {
		dao.addScrap(scrap);
	}
	
	//��ũ�� ����
	@Override
	public void removeScrap(int sno) {
		log.info("������ ����??."+sno);
		dao.removeScrap(sno);
	}
}
