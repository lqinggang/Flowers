package com.lqinggang.dao.impl.users;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.users.MemberDao;
import com.lqinggang.entity.users.Member;

@Repository(value = "memberDao")
public class MemberDaoImpl extends DaoSupport<Member> implements MemberDao {

	@Override
	public void addMember(Member member) {
		save(member);
	}

	@Override
	public void deleteMember(Member member) {
		delete(member);
	}

	@Override
	public List<Member> queryMember(int member_id) {
		String hql = " from Member as m where m.person_id.person_id='" + member_id + "'";
		List<Member> member = queryByHql(hql);
		return member;
	}

	@Override
	public void updateMember(Member member) {
		saveOrUpdate(member);
	}

}
