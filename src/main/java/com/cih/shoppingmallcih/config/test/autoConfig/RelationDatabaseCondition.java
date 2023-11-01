package com.cih.shoppingmallcih.config.test.autoConfig;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Log4j2
public class RelationDatabaseCondition implements Condition {
    // 빈 생성을 결정하는 Condition 인터페이스 구현체

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // context에서 제공 하는 것들
        //        BeanDefinitionRegistry getRegistry();
        //        ConfigurableListableBeanFactory getBeanFactory();
        //        Environment getEnvironment();
        //        ResourceLoader getResourceLoader();
        //        ClassLoader getClassLoader();

        // (방법1) application.properties 파일에 커스텀 프로퍼티가 존재 하는지 체크
        Environment env = context.getEnvironment();
        boolean bool = env.containsProperty("cih.mariadb");    //magic 프로퍼티 체크
        String value = env.getProperty("cih.mariadb");
        log.warn("application.properties-cih.mariadb value :" + value + ", isExist:" + bool);


        //(방법2)
        return isMySQLDatabase();
    }

    private boolean isMySQLDatabase(){
        try{
            // 클래스패스에 mariadb 데이터베이스 드라이버가 있으면 true

            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");

            log.warn("com.mysql.jdbc.Driver found~~~~~");
            return true;
        }catch(ClassNotFoundException e){
            log.warn("com.mysql.jdbc.Driver not found~~~~~");
            return false;
        }
    }
}
