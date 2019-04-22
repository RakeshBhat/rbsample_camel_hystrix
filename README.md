##1- test userData_successfully fails in UserRouteWithHystrixTest, as the get route definition call returns null, can't find HTTP "to" route routeId in the camelContext. If I comment the fall-back bean routeId, then only it can find the HTTP "to" route. Is it I can test only one scenario in Hystrix test ?   
public void weaveMockUserService() throws Exception{
RouteDefinition rd = camelContext.getRouteDefinition("withHystrixId");
assertNotNull(rd); <<< assert fails
