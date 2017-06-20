/*
 *EmbeddedTomcatConfiguration.java
 *
 *
 * Copyright (c) 2016 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary
 * information of Southwest Airlines, Co.
 */
package com.lm.sbux.poc.application;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddedTomcatConfiguration {

   private String additionalPorts="8881,8882";

   @Bean
   public EmbeddedServletContainerFactory servletContainer() {
      TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
      Connector[] additionalConnectors = this.additionalConnector();
      if (additionalConnectors != null && additionalConnectors.length > 0) {
         tomcat.addAdditionalTomcatConnectors(additionalConnectors);
      }
      return tomcat;
   }

   private Connector[] additionalConnector() {

      String[] ports = this.additionalPorts.split(",");
      List<Connector> result = new ArrayList<>();
      for (String port : ports) {
         Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
         connector.setScheme("http");
         connector.setPort(Integer.valueOf(port));
         result.add(connector);
      }
      return result.toArray(new Connector[] {});
   }
}
