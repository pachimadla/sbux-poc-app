package com.lm.sbux.poc.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.lm.sbux.poc.eventhub.EventHub;

/**
 * Main application to run boot application.
 *
 * @author Laxman Pachimadla
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages="com.lm.sbux")
@Import(EmbeddedTomcatConfiguration.class)
public class Application {

   public static void main(String[] args) throws Exception {
      EventHub eventHub = new EventHub();
      eventHub.receiveEvent();
      SpringApplication.run(Application.class, args);
   }
}
