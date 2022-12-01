package com.spring.community.Board.Controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.community.Board.Service.BoardService;
import com.spring.community.Board.VO.BoardVO;

@Controller
@RequestMapping(value="/board/*")
public class BoardController{
	private static Logger log = Logger.getLogger(BoardController.class.getName());
	@Autowired
	private BoardService service;
	
	//�Խ��� ���
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list",service.getList());
	}
	
	//�Խ��� �ۼ� 
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register.." + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result",board.getBno());
		
		return "redirect:/board/list";
	}
	
	//�Խ��� �󼼺���
	@GetMapping("/detail")
	public void detail(int bno,BoardVO board,Model model) {
		log.info("detail"+bno);
		//�󼼺���
		model.addAttribute("detail",service.detail(bno));
		//��ȸ��
		service.UpdateHit(bno);
	}
	
	//�Խ��� ����
	@GetMapping("/remove")
	public String remove(int bno) { 
		log.info("remove.."+bno);
		service.remove(bno);
		
		return "redirect:/board/list";
	}
	
	//�Խ��� ����
	@GetMapping("/modify")
	public void modify(int bno,Model model) {
		log.info("get modify");
		model.addAttribute("board",service.detail(bno));
	}
	@PostMapping("/modify")
	public String modify(BoardVO board) {
		log.info("post modify.."+board);
		service.modify(board);
		
		return "redirect:/board/list";
		
	}
}
