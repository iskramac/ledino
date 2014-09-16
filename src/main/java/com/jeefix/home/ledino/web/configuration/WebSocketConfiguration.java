package com.jeefix.home.ledino.web.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/websocket");

  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes("/ledino");
    registry.enableSimpleBroker("/topic");
  }

  @Override
  public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
    // TODO Auto-generated method stub

  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    // TODO Auto-generated method stub

  }

  @Override
  public void configureClientOutboundChannel(ChannelRegistration registration) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
    return true;
  }
}
