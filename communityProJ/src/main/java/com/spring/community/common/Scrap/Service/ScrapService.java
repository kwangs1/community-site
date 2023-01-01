package com.spring.community.common.Scrap.Service;

import java.util.List;
import java.util.Map;

import com.spring.community.common.Criteria;
import com.spring.community.common.Scrap.VO.ScrapVO;

public interface ScrapService {
	//��ũ�� ����Ʈ
	Map<String, List> ScrapList(ScrapVO scrap,Criteria cri);
	//��ũ�� �Ͽ�����?
	boolean findScrap(ScrapVO scrap);
	//��ũ�� �߰�
	void addScrap(ScrapVO scrap);
	//��ũ�� ����
	void removeScrap(int sno);
	int getScrapTotal(String id);

}
