package com.spring.community.common.Like.Service;

import com.spring.community.common.Like.VO.LikeVO;
import com.spring.community.common.Like.VO.ReplyLikeVO;

public interface LikeService {
/* 게시글 좋아요,싫어요 */
	//좋아요 누르기
	void LikeUp(LikeVO like);
	//좋아요 취소
	void LikeDown(LikeVO like);
	//좋아요 눌렀는지
	int findLike(int bno);
	//좋아요 갯수
	int getLike(int bno, int like_type);
	
	//싫어요 누르기
	void BadUp(LikeVO like);
	//싫어요 취소
	void BadDown(LikeVO like);
	//싫어요 확인
	int findBad(int bno);
	//싫어요 갯수
	int getBad(int bno, int bad_type);

/* 댓글 좋아요,싫어요 */
	//좋아요 누르기
	void ReplyLikeUp(ReplyLikeVO replyLike);
	//좋아요 취소
	void ReplyLikeDown(ReplyLikeVO replyLike);
	//좋아요 확인
	int findReLike(int rno);
	
	//싫어요 누르기
	void ReplyBadUp(ReplyLikeVO replyLike);
	//싫어요 취소
	void ReplyBadDown(ReplyLikeVO replyLike);
	//싫어요 확인
	int findReBad(int rno);



}
