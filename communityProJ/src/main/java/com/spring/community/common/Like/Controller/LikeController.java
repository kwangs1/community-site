package com.spring.community.common.Like.Controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.community.Board.Service.BoardService;
import com.spring.community.common.Like.Service.LikeService;
import com.spring.community.common.Like.VO.LikeVO;
import com.spring.community.common.Like.VO.ReplyLikeVO;
import com.spring.community.common.Reply.Service.ReplyService;

@RestController
@RequestMapping(value="/Like")
public class LikeController {
	private static Logger log = Logger.getLogger(LikeController.class.getName());
	
	@Autowired
	private LikeService service;
	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;

/* �Խñ� ���ƿ�, �Ⱦ��*/
	//���ƿ� ������
	@PostMapping("/LikeUp")
	public void LikeUp(LikeVO like) {
		log.info("LikeUp...C..:" + like);
		boardService.likeUp_count(like.getBno());
		
		service.LikeUp(like);			
	}
	//���ƿ� ���
	@PostMapping("/LikeDown")
	public void LikeDown(LikeVO like) {
		log.info("LikeDown......:" + like);

		boardService.likeDown_count(like.getBno());
		service.LikeDown(like);
	}
	
	//�Ⱦ�� ������
	@PostMapping("/BadUp")
	public void BadUp(LikeVO like) {
		service.BadUp(like);
	}
	//�Ⱦ�� ���
	@PostMapping("/BadDown")
	public void BadDown(LikeVO like) {
		service.BadDown(like);
	}

/* ��� ���ƿ�,�Ⱦ�� */
	//���ƿ�
	@PostMapping("/ReplyLike")
	public int ReplyLike(ReplyLikeVO replyLike) {
		int Likecheck = service.findReLike(replyLike.getRno());
		
		if(Likecheck == 0) {
			service.ReplyLikeUp(replyLike);	
			replyService.ReplyLikeUp(replyLike.getRno());
		}else if(Likecheck == 1) {
			service.ReplyLikeDown(replyLike);	
			replyService.ReplyLikeDown(replyLike.getRno());
		}
		
		return Likecheck;
	}
	
	//�Ⱦ��
	@PostMapping("/ReplyBad")
	public int ReplyBad(ReplyLikeVO replyLike) {
		int Badcheck = service.findReBad(replyLike.getRno());
		
		if(Badcheck == 0) {
			service.ReplyBadUp(replyLike);
			replyService.ReplyBadUp(replyLike.getRno());
		}else if(Badcheck == 1) {
			service.ReplyBadDown(replyLike);
			replyService.ReplyBadDown(replyLike.getRno());
		}
		
		return Badcheck;
	}

}
