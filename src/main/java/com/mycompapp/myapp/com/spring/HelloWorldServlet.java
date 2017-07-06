package com.mycompapp.myapp.com.spring;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;

public class HelloWorldServlet extends SpeechletServlet {

  public HelloWorldServlet() {
    this.setSpeechlet(new HelloWorldSpeechlet());
  }
  
 /* protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.getWriter().write("Message Post successful ");
	}*/
}