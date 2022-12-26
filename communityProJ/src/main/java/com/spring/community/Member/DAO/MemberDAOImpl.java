package com.spring.community.Member.DAO;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.community.Member.VO.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	private static Logger log = Logger.getLogger(MemberDAO.class.getName());

	@Autowired
	private SqlSession session;
	
	//ȸ������
	@Override
	public void join(MemberVO member) {
		session.insert("mapper.member.join",member);
	}
	
	//id�ߺ��˻�
	@Override
	public int IdCheck(String id) {
		return session.selectOne("mapper.member.IdCheck",id);
	}
	
	//�г����ߺ��˻�
	@Override
	public int NickCheck(String nickname) {
		return session.selectOne("mapper.member.NickCheck",nickname);
	}
	
	//�α���
	@Override
	public MemberVO login(MemberVO member) {
		return session.selectOne("mapper.member.login",member);
	}
	
	//ȸ��Ż��
	@Override
	public void remove(MemberVO member) {
		log.info("ȸ��Ż�� ȭ�� ����P");
		 session.delete("mapper.member.remove",member);
	}
	@Override
	public void removeForm() {
		session.selectOne("mapper.member.removeForm");
	}
	
	//�󼼺���
	@Override
	public void info() {
		session.selectOne("mapper.member.info");
	}
	
	//��й�ȣ ����
	@Override
	public void PwModifyForm() {
		session.selectOne("mapper.member.PwModifyForm");
	}
	@Override
	public void PwModify(MemberVO member) {
		session.update("mapper.member.PwModify",member);
	}
	
	//���� ����
	@Override
	public void modifyForm() {
		session.selectOne("mapper.member.modifyForm");
	}
	@Override
	public void modify(MemberVO member) {
		session.update("mapper.member.modify",member);
	}
	
	//�̸��� ����
	@Override
	public void EmailModifyForm() {
		session.selectOne("mapper.member.EmailModifyForm");
	}
	@Override
	public void EmailModify(MemberVO member) {
		session.update("mapper.member.EmailModify",member);
	}
	
	//id ã��
	@Override
	public String idSearch(Map<String, Object> data) {
		log.info("dao..."+data);
		return session.selectOne("mapper.member.idSearch",data);
	}
	
	//pw ã��
	@Override
	public String pwSearch(Map<String, Object> data) {
		log.info("dao..."+data);
		return session.selectOne("mapper.member.pwSearch",data);
	}
}
