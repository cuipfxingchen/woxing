package com.hdsx.taxi.woxing.mqutil.message.handle;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

public interface IMQMsgHanlder {

	void dohandle(final MQAbsMsg mqmsg);

}
