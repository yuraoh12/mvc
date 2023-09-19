package Article.model;

import util.Util;

import java.util.ArrayList;

public class MemberRepository {
    private ArrayList<Member> members = new ArrayList<>();
    private int lastMemberId = 1;

    public void insert(String loginId, String loginPw, String nickname) {
        Member member = new Member(lastMemberId, loginId, loginPw, nickname, Util.getCurrentDate());
        members.add(member);
        lastMemberId++;
    }

    public Member getMemberByLoginId(String loginId) {
        for (Member member : members) {
            if(member.getLoginId().equals(loginId)) {
                return member;
            }
        }

        return null;
    }
}
