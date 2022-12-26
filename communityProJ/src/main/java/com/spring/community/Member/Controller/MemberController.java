package com.spring.community.Member.Controller;

import java.util.Random;
import java.util.logging.Logger;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.community.Member.Service.MemberService;
import com.spring.community.Member.VO.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	private static Logger log = Logger.getLogger(MemberController.class.getName());
	
	@Autowired
	private MemberService service;
	@Autowired
	private JavaMailSender mailSender;
	
	//ȸ������
	@GetMapping("/join")
	public void join() {
		log.info("ȸ������ ������");
	}
	@PostMapping("/join")
	public String join(Model model,MemberVO member) {
		log.info("ȸ������ post");
		service.join(member);
		
		return "redirect:/member/login";
	}
	
	//id�ߺ��˻�
	@PostMapping("/IdCheck")
	@ResponseBody
	public String IdCheck(String id) {
		log.info("idCheck...." + id);
		
		int result = service.IdCheck(id);
		
		if(result != 0 ) {
			return "fail";
		}else{
			return "success";
		}
	}
	//�г��� �ߺ��˻�
	@PostMapping("/NickCheck")
	@ResponseBody
	public String NickCheck(String nickname) {
		log.info("NicknameCheck.."+nickname);
		
		int result = service.NickCheck(nickname);
		
		if(result != 0 ) {
			return "fail";
		}else {
			return "success";
		}
	}
	
	//�̸��� ����
	@PostMapping("/MailCheck")
	@ResponseBody
	public String MailCheck(String email) {
		log.info("�̸��� ������ȣ �߼�");
		
		//������ȣ ����
		Random random = new Random();
		int checkNum = random.nextInt(88888)+11111;
		log.info("���� ��ȣ ����:"+checkNum);
		
		//�̸��� html
		//xml ��ϵ� ����
		String setFrom = "cckwang2345@naver.com";
		//���Ź��� �̸���
		String toMail = email;
		//���� ����
		String title = "�ò����� Ŀ�´�Ƽ �̸��� ����";
		//���� ����
		String content = "����ڰ� �������� Ȯ���ϴ� Ȯ�� �ڵ带 �Է��ϼ���."
				+"<br><br>"
				+"������ȣ��" + checkNum + "�Դϴ�";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content,true);
			mailSender.send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String num = Integer.toString(checkNum);
		return num;
	}
	
	//�α���
	@GetMapping("/login")
	public void login() {
		
	}
	@PostMapping("/login")
	public String login(@ModelAttribute MemberVO member, 
			RedirectAttributes rttr, HttpServletRequest request) {
		
		HttpSession http = request.getSession();
		MemberVO vo = service.login(member);
		
		//�����ϴ� id�ϰ��
		if(vo != null) {
			http.setAttribute("member", vo);
			return "redirect:/board/lists";
		}else {
			//�������� �������
			rttr.addFlashAttribute("result",0);
			return "redirect:/member/login";
		}
	}
	
	//�α׾ƿ�
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession http  = request.getSession();
		http.invalidate();
		
		return "redirect:/board/lists";
	}
	
	//ȸ��Ż��
	@GetMapping("/remove")
	public void remove() {
		log.info("ȸ��Ż�� ȭ�� ����");
		service.removeForm();
	}
	@PostMapping("/remove")
	public String remove(MemberVO member, HttpServletRequest request) {
		log.info("ȸ��Ż�� ȭ�� ����P");
		HttpSession http = request.getSession();
		http.invalidate();
		
		service.remove(member);
		
		return "redirect:/board/lists";
	}
	
	//�󼼺���
	@GetMapping("/info")
	public void info() {
		service.info();
	}
	
	//�������
	@GetMapping("/PwModify")
	public void PwModify() {
		service.PwModifyForm();
	}
	@PostMapping("/PwModify")
	public String PwModify(MemberVO member,HttpServletRequest request) {
		HttpSession http = request.getSession();
		http.setAttribute("member", member);
		
		http.invalidate();
		
		service.PwModify(member);
		
		return "redirect:/member/login";
	}
	
	//��������
	@GetMapping("/modify")
	public void modify() {
		service.modifyForm();
	}
	@PostMapping("/modify")
	public String modify(MemberVO member,HttpServletRequest request) {
		HttpSession http = request.getSession();
		http.setAttribute("member", member);
		
		
		service.modify(member);
		
		return "redirect:/member/info";
	}
	
	//�̸��� ����
	@GetMapping("/EmailModify")
	public void EmailModify() {
		service.EmailModifyForm();
	}
	@PostMapping("/EmailModify")
	public String EmailModify(MemberVO member,HttpServletRequest request) {
		HttpSession http = request.getSession();
		http.setAttribute("member", member);
		
		
		service.EmailModify(member);
		
		return "redirect:/member/info";
	}
	
	//id ã��
	@GetMapping("/idSearch")
	public void idSearch() {
		
	}
	@PostMapping("/idSearch")
	@ResponseBody
	public String idSearch(String nickname, String email, Model model) {	
		log.info("nick..."+nickname);
		log.info("email..."+email);
		
		String result = service.idSearch(nickname,email);
		
		return result;
	}
	
	//pw ã��
	@GetMapping("/pwSearch")
	public void pwSearch() {
		
	}
	@PostMapping("/pwSearch")
	@ResponseBody
	public String pwSearch(String id, String email, Model model) {	
		log.info("nick..."+id);
		log.info("email..."+email);
		
		String result = service.pwSearch(id,email);
		
		return result;
	}
}
