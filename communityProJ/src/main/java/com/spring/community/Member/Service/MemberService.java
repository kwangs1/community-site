package com.spring.community.Member.Service;

import com.spring.community.Member.VO.MemberVO;

public interface MemberService {
	//ȸ������
	void join(MemberVO member);
	//id�ߺ��˻�
	int IdCheck(String id);
	//�г��� �ߺ��˻�
	int NickCheck(String nickname);
	//�α���
	MemberVO login(MemberVO member);

}
