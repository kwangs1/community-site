package com.spring.community.Member.Service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.community.Member.DAO.MemberDAO;
import com.spring.community.Member.VO.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	private static Logger log = Logger.getLogger(MemberService.class.getName());

	@Autowired
	private MemberDAO dao;
	
	//ȸ������
	@Override
	public void join(MemberVO member) {
		dao.join(member);
	}	
	
	//id �ߺ��˻�
	@Override
	public int IdCheck(String id) {
		return dao.IdCheck(id);
	}	
	
	//�г��� �ߺ�
	@Override
	public int NickCheck(String nickname) {
		return dao.NickCheck(nickname);
	}	
	
	//�α���
	@Override
	public MemberVO login(MemberVO member) {
		return dao.login(member);
	}	
	
	//ȸ��Ż��
	@Override
	public void remove(MemberVO member) {
		log.info("ȸ��Ż�� ȭ�� ����P");
		 dao.remove(member);
	}
	@Override
	public void removeForm() {
		dao.removeForm();
	}
	
	//�󼼺���
	@Override
	public void info() {
		dao.info();
	}
	
	//��й�ȣ ����
	@Override
	public void PwModifyForm() {
		dao.PwModifyForm();
	}
	@Override
	public void PwModify(MemberVO member) {
		dao.PwModify(member);
	}
	
	//���� ����
	@Override
	public void modifyForm() {
		dao.modifyForm();
	}
	@Override
	public void modify(MemberVO member) {
		dao.modify(member);
	}
	
}
