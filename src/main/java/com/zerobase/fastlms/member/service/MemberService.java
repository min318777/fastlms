package com.zerobase.fastlms.member.service;


import com.zerobase.fastlms.member.model.MemberInput;
import org.springframework.stereotype.Service;


public interface MemberService {

    boolean register(MemberInput parameter);

}
