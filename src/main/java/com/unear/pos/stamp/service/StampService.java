package com.unear.pos.stamp.service;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.PosSessionInfo;

public interface StampService {

    String createStampAfterPayment(MemberSession memberSession, PosSessionInfo posInfo);
}