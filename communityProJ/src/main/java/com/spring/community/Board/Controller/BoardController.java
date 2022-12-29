package com.spring.community.Board.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.community.Board.Service.BoardService;
import com.spring.community.Board.VO.BoardVO;
import com.spring.community.common.BoardAttachVO;
import com.spring.community.common.PageMaker;
import com.spring.community.common.SearchCriteria;
import com.spring.community.common.Like.Service.LikeService;
import com.spring.community.common.Like.VO.LikeVO;
import com.spring.community.common.Like.VO.ReplyLikeVO;
import com.spring.community.common.Reply.Service.ReplyService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController{
	private static Logger log = Logger.getLogger(BoardController.class.getName());
	@Autowired
	private BoardService service;
	@Autowired
	private ReplyService replySerivce;
	@Autowired
	private LikeService likeService;
	
	
	//�Խ��� ���
	@GetMapping("/lists")
	public void list(Model model,@ModelAttribute("scri")SearchCriteria scri) {
		log.info("All-list");
		//����¡ ��ư�� ���� ��ü ����(��ü)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);	
		//�� �Խñ� ����
		pageMaker.setTotalCount(service.countList());
	
		model.addAttribute("pageMaker",pageMaker);
		//��ü
		model.addAttribute("lists",service.lists(scri));		
		//���,���ƿ� ����
		BoardVO board = new BoardVO();
		service.reply_count(board.getBno());
	}
	//�Խ��� ���(����)
	@GetMapping("/free")
	public void free(Model model,@ModelAttribute("scri")SearchCriteria scri) {
		log.info("free-list");
		//����¡ ��ư�� ���� ��ü ����(��ü)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);	
		//�� �Խñ� ����
		pageMaker.setTotalCount(service.countList());		
		model.addAttribute("pageMaker",pageMaker);
		//����
		model.addAttribute("free",service.free(scri));
		//���,���ƿ� ����
		BoardVO board = new BoardVO();
		service.reply_count(board.getBno());

	}
	//�Խ��� ���(����)
	@GetMapping("/qna")
	public void qna(Model model,@ModelAttribute("scri")SearchCriteria scri) {
		log.info("qna-list");
		//����¡ ��ư�� ���� ��ü ����(��ü)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);	
		//�� �Խñ� ����
		pageMaker.setTotalCount(service.countList());
		model.addAttribute("pageMaker",pageMaker);
		//����
		model.addAttribute("qna",service.qna(scri));
		//���,���ƿ� ����
		BoardVO board = new BoardVO();
		service.reply_count(board.getBno());

	}
	//�Խ��� ���(�ڶ�)
	@GetMapping("/brag")
	public void brag(Model model,@ModelAttribute("scri")SearchCriteria scri) {
		log.info("brag-list");
		//����¡ ��ư�� ���� ��ü ����(��ü)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		//�� �Խñ� ����
		pageMaker.setTotalCount(service.countList());
		model.addAttribute("pageMaker",pageMaker);
		//�ڶ�
		model.addAttribute("brag",service.brag(scri));
		//���,���ƿ� ����
		BoardVO board = new BoardVO();
		service.reply_count(board.getBno());

	}
	//�Խ��� ���(����)
	@GetMapping("/tip")
	public void tip(Model model,@ModelAttribute("scri")SearchCriteria scri) {
		log.info("tip-list");
		//����¡ ��ư�� ���� ��ü ����(��ü)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		//�� �Խñ� ����
		pageMaker.setTotalCount(service.countList());
		model.addAttribute("pageMaker",pageMaker);
		//����
		model.addAttribute("tip",service.tip(scri));
		//���,���ƿ� ����
		BoardVO board = new BoardVO();
		service.reply_count(board.getBno());

	}

	//�Խ��� �ۼ� 
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {

		log.info("==========================");

		log.info("register: " + board);

		if (board.getAttachList() != null) {

			board.getAttachList().forEach(attach -> log.info("getAttachList:"+attach));

		}

		log.info("==========================");

		service.register(board);

		rttr.addFlashAttribute("result", board.getBno());

		return "redirect:/board/lists";
	}
	
	//�Խ��� �󼼺���
	@GetMapping("/detail")
	public void detail(int bno,Model model,@ModelAttribute("scri")SearchCriteria scri
			,HttpServletRequest request) {
		log.info("detail"+bno);
		//�󼼺���
		model.addAttribute("detail",service.detail(bno));
		//��ȸ��
		service.UpdateHit(bno);
		//���ƿ�
		LikeVO like = new LikeVO();
		like.setBno(bno);
		like.setLike_type(1);
		like.setBad_type(1);
		
		/* �Խñ� ���ƿ�,�Ⱦ�� Ȯ�� */
		//���ƿ� Ȯ��
		model.addAttribute("findLike",likeService.findLike(bno));
		//���ƿ� ����
		model.addAttribute("getLike",likeService.getLike(bno,1));
		//�Ⱦ�� Ȯ��
		model.addAttribute("findBad",likeService.findBad(bno));
		//�Ⱦ�� ����
		model.addAttribute("getBad",likeService.getBad(bno,1));
		
	}
	//���� �˾�â
	@GetMapping("/getModifyReply")
	public void getModifyReply(int rno,Model model) {
		log.info("��� ���� �˾� Controller");
		model.addAttribute("reply",replySerivce.getModifyReply(rno));
	}
	
	//�Խ��� ����
	@GetMapping("/remove")
	public String remove(int bno,RedirectAttributes rttr,@ModelAttribute("scri")SearchCriteria scri) { 
		log.info("remove.."+bno);
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		if(service.remove(bno)) {
			deleteFiles(attachList);
		}
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/board/lists";
	}
	
	//�Խ��� ����
	@GetMapping("/modify")
	public void modify(int bno,Model model,@ModelAttribute("scri")SearchCriteria scri) {
		log.info("get modify");
		model.addAttribute("board",service.detail(bno));
	}
	@PostMapping("/modify")
	public String modify(BoardVO board,RedirectAttributes rttr,@ModelAttribute("scri")SearchCriteria scri) {
		log.info("post modify.."+board);
		service.modify(board);
		
		rttr.addAttribute("bno", board.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/board/detail";
		
	}
	
	//���� ���� �޼���
	//BoardAttachVO�� �� List�� ����ش�
	private void deleteFiles(List<BoardAttachVO> attachList) {
	    //���� list�ȿ� ���� ���ų� �迭�� Ÿ�� ���̰� 0 �̸� ������ ��Ų��
	    if(attachList == null || attachList.size() == 0) {
	      return;
	    }
	    
	    log.info("delete attach files...................");
	    log.info("delete attachList"+attachList);
	    //�����Լ��� ����ؼ� �迭�� ��� ���� �Ű����� attach�� ���� ���
	    attachList.forEach(attach -> {
	      try {        
	    	  //������ ��ο� �ִ� �̹��� ������ ���� ������ file��ü�� �ִ´�
	        Path file  = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\" + attach.getUuid()+"_"+ attach.getFileName());
	        //deleteIfExists() �� ����ؼ� ������ �����ϸ� ����, �������������� false�� �����ϰ� ��
	        Files.deleteIfExists(file);
	        //���� ���Ͽ� �Էµ� ���� image�� �����Ѵٸ�
	        if(Files.probeContentType(file).startsWith("image")) {
	        	//thumbNail������ �� ��ο� �ִ� ���� ���� �ְ�
	          Path thumbNail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_" + attach.getUuid()+"_"+ attach.getFileName());
	          //������Ų��
	          Files.delete(thumbNail);
	        }
	
	      }catch(Exception e) {
	        log.info("delete file error" + e.getMessage());
	      }//end catch
	    });//end foreachd
	  }

	
	//����� �̹��� ������ List�� ���� ��½�Ű�� ����
	@GetMapping(value = "/getAttachList",
			    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(int bno) {

		log.info("getAttachList " + bno);

		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);

	}
}
