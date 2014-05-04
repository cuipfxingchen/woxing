package com.hdsx.taxi.woxing.web.guice;

import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		super.requestStaticInjection(GuiceFactory.class);
	}

}
