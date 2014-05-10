package com.hdsx.taxi.woxing.mqutil.guice;

import com.google.inject.AbstractModule;

public class MqGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		super.requestStaticInjection(MqGuiceFactory.class);
	}

}
