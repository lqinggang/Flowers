package com.lqinggang.dao.users;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.users.Member;

/**
 * 会员信息
 * 
 * @author LQingGang
 * @time 2018年3月22日 - 上午11:25:32
 */
public interface MemberDao extends BaseDao<Member> {

	/**
	 * 新增会员信息
	 * 
	 * @param member
	 * @return void
	 */
	public void addMember(Member member);

	/**
	 * 删除会员信息
	 * 
	 * @param member
	 * @return void
	 */
	public void deleteMember(Member member);

	/**
	 * 通过会员ID查询会员信息
	 * 
	 * @param member_id
	 * @return Member
	 */
	public List<Member> queryMember(int member_id);

	/**
	 * 更新会员信息
	 * 
	 * @param member
	 * @return void
	 */
	public void updateMember(Member member);

}
