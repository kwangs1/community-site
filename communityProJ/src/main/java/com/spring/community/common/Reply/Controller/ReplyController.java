package com.spring.community.common.Reply.Controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.community.Board.Service.BoardService;
import com.spring.community.common.Reply.Service.ReplyService;
import com.spring.community.common.Reply.VO.ReplyVO;

@RestController
@RequestMapping(value="/reply")
public class ReplyController {
	@Autowired
	private ReplyService service;
	@Autowired
	private BoardService boardService;
	
	private static Logger log = Logger.getLogger(ReplyController.class.getName());
	
	//��۸��
	@PostMapping("/ReplyList")
	public List<ReplyVO> ReplyList(int bno,Model model){
		//��� ��
		boardService.reply_count(bno);

		return service.ReplyList(bno);
	}
	
	//����ۼ�
	@PostMapping("/addReply")
	public ResponseEntity<String> addReply(@RequestBody ReplyVO reply){
		log.info("ReplyVO:"+ reply);
			
		return service.addReply(reply) == 1  
				?  new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//��ۼ���
	@PostMapping("/ModifyReply")
	public ResponseEntity<String> ModifyReply(@RequestBody ReplyVO reply){
		log.info("modify:" + reply);

		return service.ModifyReply(reply) == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//��ۻ���
	@PostMapping("/DeleteReply")
	public ResponseEntity<String> DeleteReply(ReplyVO reply){
		
		return service.DeleteReply(reply) == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//���� �ۼ�
	@PostMapping("/addReReply")
	public ResponseEntity<String> addRe_Reply(@RequestBody ReplyVO reply){	
		log.info("ReRepy-C:"+ reply);
		//����� r_group�� ������������ set����
		int rno = reply.getRno();
		reply.setR_group(rno);
		//���� �̶�� ���� �����ϱ����� r_depth �� ����
		reply.setR_depth(1);
		
	return service.addRe_Reply(reply) == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
