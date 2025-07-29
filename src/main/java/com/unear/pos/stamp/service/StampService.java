package com.unear.pos.stamp.service;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.PosSessionInfo;

public interface StampService {

    void createStampAfterPayment(MemberSession memberSession, PosSessionInfo posInfo);
}