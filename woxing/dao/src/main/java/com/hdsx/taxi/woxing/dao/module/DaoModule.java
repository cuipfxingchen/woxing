package com.hdsx.taxi.woxing.dao.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.bonecp.BoneCPProvider;

import com.google.inject.name.Names;
import com.hdsx.taxi.woxing.dao.ComplaintMapper;
import com.hdsx.taxi.woxing.dao.EstimateMapper;
import com.hdsx.taxi.woxing.dao.OrderMapper;

//public class MybatisModule implements Module {
public class DaoModule extends MyBatisModule {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(DaoModule.class);

	@Override
	protected void initialize() {
		Properties myBatisProperties = new Properties();
		ResourceBundle rb = ResourceBundle.getBundle("db");
		myBatisProperties.setProperty("mybatis.environment.id",
				rb.getString("JDBC.Environmet.id"));
		myBatisProperties.setProperty("derby.create",
				rb.getString("derby.create"));
		myBatisProperties.setProperty("JDBC.driver",
				rb.getString("JDBC.driver"));
		myBatisProperties.setProperty("JDBC.url", rb.getString("JDBC.url"));
		myBatisProperties.setProperty("JDBC.username",
				rb.getString("JDBC.username"));
		myBatisProperties.setProperty("JDBC.password",
				rb.getString("JDBC.password"));
		myBatisProperties.setProperty("JDBC.autoCommit",
				rb.getString("JDBC.autoCommit"));
		myBatisProperties.setProperty("JDBC.Sch", rb.getString("JDBC.Sch"));
		myBatisProperties.setProperty("bonecp.lazyInit",
				rb.getString("bonecp.lazyInit"));
		myBatisProperties.setProperty("ldap.url", rb.getString("ldap.url"));
		myBatisProperties.setProperty("ldap.username",
				rb.getString("ldap.username"));
		myBatisProperties.setProperty("ladp.password",
				rb.getString("ladp.password"));
		Names.bindProperties(this.binder(), myBatisProperties);
		bindTransactionFactoryType(JdbcTransactionFactory.class);
		bindDataSourceProviderType(BoneCPProvider.class);

		addMapperClass(OrderMapper.class);
		addMapperClass(ComplaintMapper.class);
		addMapperClass(EstimateMapper.class);

		logger.info("完成mybatis配置");
	}

}
