package com.hdsx.taxi.woxing.mqutil.message.handle;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

public abstract class AbsMQMsgHanlder implements IMQMsgHanlder {

	public void dohandle(final MQAbsMsg mqmsg) {
		Thread th = new Thread() {

			@Override
			public void run() {
				dowith(mqmsg);
			}

		};
		th.start();

	}

	protected abstract void dowith(MQAbsMsg msg);

}
