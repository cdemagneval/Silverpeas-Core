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

package com.silverpeas.session;

import com.stratelia.webactiv.beans.admin.UserDetail;

/**
 * It gathers information about an opened session of a user.
 */
public class SessionInfo {

  private String sessionId;
  private UserDetail userDetail;
  private long openingTimestamp;
  private long lastAccessTimestamp;

  /**
   * Constructs a new instance about a given opened user session.
   * @param sessionId the identifier of the opened session.
   * @param user the user for which a session was opened.
   */
  public SessionInfo(final String sessionId, final UserDetail user) {
    this.sessionId = sessionId;
    this.userDetail = user;
    this.openingTimestamp = this.lastAccessTimestamp = System.currentTimeMillis();
  }

  public long getLastAccessTimestamp() {
    return lastAccessTimestamp;
  }

  public long getOpeningTimestamp() {
    return openingTimestamp;
  }

  public String getSessionId() {
    return sessionId;
  }

  public UserDetail getUserDetail() {
    return userDetail;
  }

  public void updateLastAccess() {
    this.lastAccessTimestamp = System.currentTimeMillis();
  }
}
