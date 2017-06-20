/*
 *EventHub.java
 *
 *
 * Copyright (c) 2016 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary
 * information of Southwest Airlines, Co.
 */
package com.lm.sbux.poc.eventhub;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Component;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.PartitionReceiver;
import com.microsoft.azure.eventhubs.PartitionSender;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.ServiceBusException;

@Component
public class EventHub {

   private String partitionId = "3";

   public void sendEvent(String message) throws ServiceBusException, ExecutionException,
   InterruptedException, IOException {
      ConnectionStringBuilder connStr = new ConnectionStringBuilder(
            Properties.NAMESPACE, Properties.EVENTHUB_NAME,
            Properties.SAS_KEY_NAME, Properties.SAS_KEY);
      byte[] payloadBytes = message.getBytes("UTF-8");
      System.out.println("payloadBytes=  " + payloadBytes);
      EventData sendEvent = new EventData(payloadBytes);
      EventHubClient ehClient = EventHubClient
            .createFromConnectionStringSync(connStr.toString());
      PartitionSender sender = ehClient.createPartitionSender(partitionId).get();
      sender.sendSync(sendEvent);
      System.out.println("message sent successfull..");

   }

   public void receiveEvent() throws ServiceBusException, ExecutionException,
   InterruptedException, IOException {
      ConnectionStringBuilder eventHubConnectionString = new ConnectionStringBuilder(Properties.NAMESPACE, Properties.EVENTHUB_NAME, Properties.SAS_KEY_NAME, Properties.SAS_KEY);
      //EventProcessorHost host = new EventProcessorHost("", "", "", "","","");
      //System.out.println("Registering host named " + host.getHostName());
      //EventProcessorOptions options = new EventProcessorOptions();
      //options.setExceptionNotification(new ErrorNotificationHandler());
      try
      {
         // host.registerEventProcessor(EventProcessor.class, options).get();
         EventHubClient ehClient = EventHubClient.createFromConnectionStringSync(eventHubConnectionString.toString());

         PartitionReceiver receiver = ehClient.createReceiverSync(
               EventHubClient.DEFAULT_CONSUMER_GROUP_NAME,
               partitionId,
               PartitionReceiver.START_OF_STREAM,
               false);

         receiver.setReceiveTimeout(Duration.ofSeconds(50));
         Iterable<EventData> receivedEvents = receiver.receiveSync(10);
         for (EventData eventData : receivedEvents) {
            System.out.println("Message : "+eventData.getObject().toString());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.out.print("Failure while registering: ");
         if (e instanceof ExecutionException)
         {
            Throwable inner = e.getCause();
            System.out.println(inner.toString());
         }
         else
         {
            System.out.println(e.toString());
         }
      }
      System.out.println("End of sample");
   }


   /* public static void main(String[] args) {
      EventHub eventHub=new EventHub();
      try {
         eventHub.sendEvent("SBUX Event Hub Test meaasage");
         //Uncomment below to receiveEvent
         //eventHub.receiveEvent();
      } catch (ServiceBusException e) {
         e.printStackTrace();
      } catch (ExecutionException e) {

         e.printStackTrace();
      } catch (InterruptedException e) {

         e.printStackTrace();
      } catch (IOException e) {

         e.printStackTrace();
      }
   }*/

}
