package com.spring.community.common.Scrap.Controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.community.Member.VO.MemberVO;
import com.spring.community.common.Criteria;
import com.spring.community.common.PageMaker;
import com.spring.community.common.Scrap.Service.ScrapService;
import com.spring.community.common.Scrap.VO.ScrapVO;

@Controller
@RequestMapping("/Scrap")
public class ScrapController {
	private static Logger log = Logger.getLogger(ScrapController.class.getName());

	@Autowired
	private ScrapService service;

	//��ũ�� ����Ʈ
	@GetMapping("/ScrapList")
	public void ScrapList(HttpServletRequest request,ScrapVO scrap,Criteria cri,Model model) {
		HttpSession session = request.getSession();
		
		//id�� ���� ���� ������
		MemberVO member = (MemberVO)session.getAttribute("member");
		String id = member.getId();
		scrap.setId(id);
		cri.setId(id);
		
		//��ũ�� �� �Խñ��� Map�� ����.
		Map<String,List>map = service.ScrapList(scrap,cri);
		session.setAttribute("scrap", map);
		
		//����¡ ��ư�� ���� ��ü ����(��ü)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);	
		//�� �Խñ� ����
		pageMaker.setTotalCount(service.getScrapTotal(cri.getId()));
		log.info("cri.getId()"+cri.getId());
		model.addAttribute("pageMaker",pageMaker);
	}
	
	//��ũ�� �߰� �� Ȯ��
	@PostMapping("/addScrap")
	@ResponseBody
	public String addScrap(int bno, HttpServletRequest request, ScrapVO scrap) {
		HttpSession session = request.getSession();
		
		//id�� ���� ���� ������
		MemberVO member = (MemberVO)session.getAttribute("member");
		String id = member.getId();
		scrap.setId(id);
		//�Խñ� ��ȣ ����
		scrap.setBno(bno);
		
		//�ش� �Խñ��� ��ũ�� �Ͽ�����?
		boolean ScrapCheck = service.findScrap(scrap);
		log.info("�Խù��� üũ �Ͽ����ǰ���?..."+ScrapCheck);
		
		if(ScrapCheck == true) {
			return "fail";
		}else {
			service.addScrap(scrap);
			return "success";
		}
		
	}
	
	//��ũ�� ����
	@PostMapping("/removeScrap")
	public String removeScrap(int sno) {
		log.info("������ ����??."+sno);
		service.removeScrap(sno);
		
		return "redirect:/Scrap/ScrapList";
	}
	
}
