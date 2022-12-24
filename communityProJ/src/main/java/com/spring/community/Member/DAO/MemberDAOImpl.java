package com.spring.community.Member.DAO;

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
}
