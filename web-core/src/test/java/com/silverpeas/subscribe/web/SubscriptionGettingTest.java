/*
 * Copyright (C) 2000 - 2011 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/legal/licensing"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.silverpeas.subscribe.web;

import java.util.List;
import com.silverpeas.subscribe.service.Subscription;
import org.junit.Before;
import java.util.Collection;
import com.google.common.collect.Lists;
import com.silverpeas.subscribe.SubscriptionService;
import com.silverpeas.rest.mock.UserDetailWithProfiles;
import com.stratelia.webactiv.SilverpeasRole;
import com.silverpeas.rest.RESTWebServiceTest;
import com.silverpeas.subscribe.SubscriptionServiceFactory;
import com.silverpeas.subscribe.service.ComponentSubscription;
import com.silverpeas.util.ForeignPK;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static com.silverpeas.rest.RESTWebService.*;
import static com.silverpeas.subscribe.web.SubscriptionTestResources.*;

public class SubscriptionGettingTest extends RESTWebServiceTest<SubscriptionTestResources> {

  public SubscriptionGettingTest() {
    super("com.silverpeas.subscribe.web", "spring-subscription-webservice.xml");
  }
  
  @Override
  public String[] getExistingComponentInstances() {
    return new String[] {COMPONENT_ID};
  }

  @Before
  public void setup() {
    assertThat(getTestResources().getMockableSubscriptionService(),
            is(SubscriptionServiceFactory.getFactory().getSubscribeService()));
  }

  @Test
  public void getSubscriptionsByANonAuthenticatedUser() {
    WebResource resource = resource();
    try {
      resource.path(SUBSCRIPTION_RESOURCE_PATH).accept(MediaType.APPLICATION_JSON).get(String.class);
      fail("A non authenticated user shouldn't access the comment");
    } catch (UniformInterfaceException ex) {
      int recievedStatus = ex.getResponse().getStatus();
      int unauthorized = Status.UNAUTHORIZED.getStatusCode();
      assertThat(recievedStatus, is(unauthorized));
    }
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getComponentSubscriptionByAnAuthenticatedUser() throws Exception {
    WebResource resource = resource();
    UserDetailWithProfiles user = new UserDetailWithProfiles();
    user.setFirstName("Bart");
    user.setLastName("Simpson");
    user.setId("10");
    user.addProfile(COMPONENT_ID, SilverpeasRole.writer);
    user.addProfile(COMPONENT_ID, SilverpeasRole.user);
    String sessionKey = authenticate(user);
    SubscriptionService mockedSubscriptionService = mock(SubscriptionService.class);
    ComponentSubscription subscription = new ComponentSubscription("10", COMPONENT_ID);
    Collection<ComponentSubscription> subscriptions = Lists.newArrayList(subscription);
    when((Collection<ComponentSubscription>) mockedSubscriptionService.
            getUserSubscriptionsByComponent("10", COMPONENT_ID)).thenReturn(subscriptions);
    getTestResources().getMockableSubscriptionService().setImplementation(mockedSubscriptionService);
    SubscriptionEntity[] entities = resource.path(SUBSCRIPTION_RESOURCE_PATH).header(HTTP_SESSIONKEY, sessionKey).
            accept(MediaType.APPLICATION_JSON).get(SubscriptionEntity[].class);
    assertNotNull(entities);
    assertThat(entities.length, is(1));
    assertThat(entities[0], SubscriptionEntityMatcher.matches((Subscription) subscription));
  }
  
  
  @Test
  @SuppressWarnings("unchecked")
  public void getComponentSubscribersByAnAuthenticatedUser() throws Exception {
    WebResource resource = resource();
    UserDetailWithProfiles user = new UserDetailWithProfiles();
    user.setFirstName("Bart");
    user.setLastName("Simpson");
    user.setId("10");
    user.addProfile(COMPONENT_ID, SilverpeasRole.writer);
    user.addProfile(COMPONENT_ID, SilverpeasRole.user);
    String sessionKey = authenticate(user);
    SubscriptionService mockedSubscriptionService = mock(SubscriptionService.class);
    List<String> subscribers = Lists.newArrayList("5", "6", "7", "20");
    when(mockedSubscriptionService.getSubscribers(new ForeignPK("0", COMPONENT_ID))).thenReturn(subscribers);
    getTestResources().getMockableSubscriptionService().setImplementation(mockedSubscriptionService);
    String[] entities = resource.path(SUBSCRIPTION_RESOURCE_PATH + "/subscribers/0").header(HTTP_SESSIONKEY, sessionKey).
            accept(MediaType.APPLICATION_JSON).get(String[].class);
    assertNotNull(entities);
    assertThat(entities.length, is(4));
    assertThat(entities, is(new String[]{"5", "6", "7", "20"}));
  }
}
