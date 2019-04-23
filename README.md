##1- test userData_successfully fails in UserRouteWithHystrixTest, as the get route definition call returns null, can't find HTTP "to" route routeId in the camelContext. If I comment the fall-back bean routeId, then only it can find the HTTP "to" route. Is it I can test only one scenario in Hystrix test ?   
public void weaveMockUserService() throws Exception{
RouteDefinition rd = camelContext.getRouteDefinition("withHystrixId");
assertNotNull(rd); <<< assert fails
java.lang.AssertionError
	at org.junit.Assert.fail(Assert.java:86)
	at org.junit.Assert.assertTrue(Assert.java:41)
	at org.junit.Assert.assertNotNull(Assert.java:712)
	at org.junit.Assert.assertNotNull(Assert.java:722)
	at com.example.demo.UserRouteWithHystrixTest.weaveMockUserService(UserRouteWithHystrixTest.java:116)
	at com.example.demo.UserRouteWithHystrixTest.userData_successfully(UserRouteWithHystrixTest.java:62)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.springframework.test.context.junit4.statements.RunBeforeTestExecutionCallbacks.evaluate(RunBeforeTestExecutionCallbacks.java:74)
	at org.springframework.test.context.junit4.statements.RunAfterTestExecutionCallbacks.evaluate(RunAfterTestExecutionCallbacks.java:84)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:251)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:97)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:190)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:678)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)


===============================================================================================================
log
===============================================================================================================

 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.3.RELEASE)

:48.266  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : The following profiles are active: utest
:48.872  INFO 4932 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.apache.camel.spring.boot.CamelAutoConfiguration' of type [org.apache.camel.spring.boot.CamelAutoConfiguration$$EnhancerBySpringCGLIB$$e1886ef3] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
:49.046  INFO 4932 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 0 (http)
:49.048  INFO 4932 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
:49.048  INFO 4932 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.16]
:49.078  INFO 4932 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
:49.078  INFO 4932 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 808 ms
:49.709  INFO 4932 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
:49.933  INFO 4932 --- [           main] o.a.c.i.converter.DefaultTypeConverter   : Type converters loaded (core: 195, classpath: 11)
:50.769  INFO 4932 --- [           main] o.a.camel.spring.boot.RoutesCollector    : Loading additional Camel XML routes from: classpath:camel/*.xml
:50.770  INFO 4932 --- [           main] o.a.camel.spring.boot.RoutesCollector    : Loading additional Camel XML rests from: classpath:camel-rest/*.xml
:50.777  INFO 4932 --- [           main] o.a.camel.spring.SpringCamelContext      : Apache Camel 2.23.1 (CamelContext: camel-3) is starting
:50.779  INFO 4932 --- [           main] o.a.c.m.ManagedManagementStrategy        : JMX is enabled
:50.921  INFO 4932 --- [           main] o.a.camel.component.http4.HttpComponent  : Created ClientConnectionManager org.apache.http.impl.conn.PoolingHttpClientConnectionManager@1cfa7ee0
:50.957  INFO 4932 --- [           main] o.a.camel.spring.SpringCamelContext      : StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
:51.009  INFO 4932 --- [           main] o.a.camel.spring.boot.RoutesCollector    : Starting CamelMainRunController to ensure the main thread keeps running
:51.011  INFO 4932 --- [           main] o.a.camel.spring.SpringCamelContext      : Route: route5 started and consuming from: direct://mainRoute
:51.012  INFO 4932 --- [           main] o.a.camel.spring.SpringCamelContext      : Route: route6 started and consuming from: direct://routeWithOutHystrix
:51.014  INFO 4932 --- [           main] o.a.camel.spring.SpringCamelContext      : Route: getCachedDataId started and consuming from: direct://UserRouteWithHystrix
:51.015  INFO 4932 --- [           main] o.a.camel.spring.SpringCamelContext      : Route: userHostWithOutHystrixId started and consuming from: direct://UserRouteWithOutHystrixRoute
:51.015  INFO 4932 --- [           main] o.a.camel.spring.SpringCamelContext      : Total 4 routes, of which 4 are started
:51.015  INFO 4932 --- [           main] o.a.camel.spring.SpringCamelContext      : Apache Camel 2.23.1 (CamelContext: camel-3) started in 0.238 seconds
:51.030  INFO 4932 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 49312 (http) with context path ''
:51.031  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : Started UserRouteWithHystrixTest in 2.819 seconds (JVM running for 59.827)
:51.041  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : Waiting for Camel Context to become initialized.
:56.042  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : getRouteDefinitions().getId route5
:56.042  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : getRouteDefinitions().getId route6
:56.042  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : getRouteDefinitions().getId getCachedDataId
:56.043  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : getRouteDefinitions().getId userHostWithOutHystrixId
:56.051  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : ********************************************************************************
:56.051  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : Testing done: userData_successfully(com.example.demo.UserRouteWithHystrixTest)
:56.051  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : Took: 8.116 seconds (8116 millis)
:56.051  INFO 4932 --- [           main] c.example.demo.UserRouteWithHystrixTest  : ********************************************************************************
:56.052  INFO 4932 --- [           main] a.c.t.s.CamelSpringBootExecutionListener : @RunWith(CamelSpringBootRunner.class) after: class com.example.demo.UserRouteWithHystrixTest.userData_successfully
:56.117  INFO 4932 --- [ngupInterceptor] o.a.c.m.MainSupport$HangupInterceptor    : Received hang up - stopping the main instance.
:56.118  INFO 4932 --- [ngupInterceptor] o.a.c.m.MainSupport$HangupInterceptor    : Received hang up - stopping the main instance.
:56.121  INFO 4932 --- [ngupInterceptor] o.a.camel.spring.SpringCamelContext      : Apache Camel 2.23.1 (CamelContext: camel-3) is shutting down
:56.118  INFO 4932 --- [ngupInterceptor] o.a.c.m.MainSupport$HangupInterceptor    : Received hang up - stopping the main instance.
:56.121  INFO 4932 --- [ngupInterceptor] o.a.camel.impl.DefaultShutdownStrategy   : Starting to graceful shutdown 4 routes (timeout 300 seconds)
:56.137  INFO 4932 --- [      Thread-13] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
:56.161  INFO 4932 --- [ - ShutdownTask] o.a.camel.impl.DefaultShutdownStrategy   : Route: userHostWithOutHystrixId shutdown complete, was consuming from: direct://UserRouteWithOutHystrixRoute
:56.163  INFO 4932 --- [ - ShutdownTask] o.a.camel.impl.DefaultShutdownStrategy   : Route: getCachedDataId shutdown complete, was consuming from: direct://UserRouteWithHystrix
:56.164  INFO 4932 --- [ - ShutdownTask] o.a.camel.impl.DefaultShutdownStrategy   : Route: route6 shutdown complete, was consuming from: direct://routeWithOutHystrix
:56.164  INFO 4932 --- [ - ShutdownTask] o.a.camel.impl.DefaultShutdownStrategy   : Route: route5 shutdown complete, was consuming from: direct://mainRoute
:56.166  INFO 4932 --- [ngupInterceptor] o.a.camel.impl.DefaultShutdownStrategy   : Graceful shutdown of 4 routes completed in 0 seconds
:56.169  INFO 4932 --- [ngupInterceptor] o.a.camel.main.MainLifecycleStrategy     : CamelContext: camel-3 has been shutdown, triggering shutdown of the JVM.
:56.171  INFO 4932 --- [ngupInterceptor] o.a.camel.spring.SpringCamelContext      : Apache Camel 2.23.1 (CamelContext: camel-3) uptime 5.394 seconds
:56.171  INFO 4932 --- [ngupInterceptor] o.a.camel.spring.SpringCamelContext      : Apache Camel 2.23.1 (CamelContext: camel-3) is shutdown in 0.050 seconds
