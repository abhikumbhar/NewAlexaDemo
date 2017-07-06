package com.mycompapp.myapp;

import com.amazon.speech.Sdk;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import com.google.appengine.api.utils.SystemProperty;
import com.mycompapp.myapp.com.spring.HelloWorldSpeechlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class HelloAppEngine extends HttpServlet {

	/**
	 * port number for the jetty server.
	 */
	private static final int PORT = 8443;

	/**
	 * Security scheme to use.
	 */
	private static final String HTTPS_SCHEME = "https";

	
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
	  
	  
	  Properties properties = System.getProperties();

	    response.setContentType("text/plain");
	    response.getWriter().println("Hello App Engine Get - Standard using "
	        + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));
	  
	// Configure logging to output to the console with default level of INFO
	    BasicConfigurator.configure();

	    // Configure server and its associated servlets
	    Server server = new Server();
	    SslConnectionFactory sslConnectionFactory = new SslConnectionFactory();
	    System.out.println("SSL protocol="+sslConnectionFactory.getProtocol());
	    
	    SslContextFactory sslContextFactory = sslConnectionFactory.getSslContextFactory();
	    
	   sslContextFactory.setKeyStorePath("src/main/webapp/MyNewJks.jks");
	    //sslContextFactory.setKeyStorePath("./MyNewJks.jks");
	    

	    //sslContextFactory.setKeyStorePassword(System.getProperty("javax.net.ssl.keyStorePassword"));
	    sslContextFactory.setKeyStorePassword("test123");
	    sslContextFactory.setIncludeCipherSuites(Sdk.SUPPORTED_CIPHER_SUITES);
	    
	    HttpConfiguration httpConf = new HttpConfiguration();
	    
	    
	    httpConf.setSecurePort(PORT);
	    httpConf.setSecureScheme(HTTPS_SCHEME);
	    httpConf.addCustomizer(new SecureRequestCustomizer());
	    
	    HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConf);
	    
	    ServerConnector serverConnector =
	            new ServerConnector(server,sslConnectionFactory, httpConnectionFactory);
	    serverConnector.setPort(PORT);

	    Connector[] connectors = new Connector[1];
	    connectors[0] = serverConnector;
	    server.setConnectors(connectors);

	    
	    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	    context.setContextPath("/");
	    server.setHandler(context);
	    context.addServlet(new ServletHolder(createServlet(new HelloWorldSpeechlet())), "/hello");
	    try {
			server.start();
		    server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	  
/*
    Properties properties = System.getProperties();

    response.setContentType("text/plain");
    response.getWriter().println("Hello App Engine - Standard using "
        + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));
*/  }
  
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
	  
	    Properties properties = System.getProperties();

	    response.setContentType("text/plain");
	    response.getWriter().println("Hello App Engine - Standard using "
	        + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));
	  
	// Configure logging to output to the console with default level of INFO
	    BasicConfigurator.configure();

	    // Configure server and its associated servlets
	    Server server = new Server();
	    SslConnectionFactory sslConnectionFactory = new SslConnectionFactory();
	    System.out.println("SSL protocol="+sslConnectionFactory.getProtocol());
	    
	    SslContextFactory sslContextFactory = sslConnectionFactory.getSslContextFactory();
	    
	   sslContextFactory.setKeyStorePath("src/main/webapp/MyNewJks.jks");
	    //sslContextFactory.setKeyStorePath("./MyNewJks.jks");
	    

	    //sslContextFactory.setKeyStorePassword(System.getProperty("javax.net.ssl.keyStorePassword"));
	    sslContextFactory.setKeyStorePassword("test123");
	    sslContextFactory.setIncludeCipherSuites(Sdk.SUPPORTED_CIPHER_SUITES);
	    
	    HttpConfiguration httpConf = new HttpConfiguration();
	    
	    
	    httpConf.setSecurePort(PORT);
	    httpConf.setSecureScheme(HTTPS_SCHEME);
	    httpConf.addCustomizer(new SecureRequestCustomizer());
	    
	    HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConf);
	    
	    ServerConnector serverConnector =
	            new ServerConnector(server,sslConnectionFactory, httpConnectionFactory);
	    serverConnector.setPort(PORT);

	    Connector[] connectors = new Connector[1];
	    connectors[0] = serverConnector;
	    server.setConnectors(connectors);

	    
	    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	    context.setContextPath("/");
	    server.setHandler(context);
	    context.addServlet(new ServletHolder(createServlet(new HelloWorldSpeechlet())), "/hello");
	    try {
			server.start();
		    server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	  


  }
  
  private static SpeechletServlet createServlet(final Speechlet speechlet) {
	   SpeechletServlet servlet = new SpeechletServlet();
	  //HelloWorldServlet servlet = new HelloWorldServlet();
	    servlet.setSpeechlet(speechlet);
	    return servlet;
	}

  public static String getInfo() {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }

}
