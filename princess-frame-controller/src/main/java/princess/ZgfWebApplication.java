package princess;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

/**
 * 应用程序入口类
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = { "princess.*" }, annotationClass = Mapper.class)
@Slf4j
public class ZgfWebApplication extends SpringBootServletInitializer {

	/**
	 * 应用入口
	 * 
	 * @param args 参数
	 */
	public static void main(String[] args) {
		// 解决netty冲突
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication.run(ZgfWebApplication.class, args);
		log.info("[Startup Success]");
	}
}
   