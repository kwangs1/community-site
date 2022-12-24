package com.spring.community.Member.DAO;

import com.spring.community.Member.VO.MemberVO;

public interface MemberDAO {
	//ȸ������
	void join(MemberVO member);
	//id �ߺ��˻�
	int IdCheck(String id);
	//�г��� �ߺ��˻�
	int NickCheck(String nickname);
	//�α���
	MemberVO login(MemberVO member);
	//ȸ��Ż��
	void remove(MemberVO member);
	void removeForm();
	//�󼼺���
	void info();
	//��й�ȣ ����
	void PwModifyForm();
	void PwModify(MemberVO member);
	//���� ����
	void modifyForm();
	void modify(MemberVO member);

}
