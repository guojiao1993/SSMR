package com.ssm.chapter10.annotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
public class DataSourceCondition implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//��ȡ�����Ļ���
		Environment env = context.getEnvironment();
		//�ж��Ƿ���ڹ�������Դ�Ļ�������
		return env.containsProperty("jdbc.database.driver") 
				&& env.containsProperty("jdbc.database.url")
				&& env.containsProperty("jdbc.database.username")
				&& env.containsProperty("jdbc.database.password");
	}
}
