/*
 * Copyright (C) 2000 - 2009 Silverpeas
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
package com.silverpeas.accesscontrol;

import com.stratelia.silverpeas.peasCore.MainSessionController;

/**
 *
 * @param <T> The type of object we are checking the access on.
 * @author ehugonnet
 */
public interface AccessController<T> {
  
  /**
   * Check if the current user may access the specified object.
   * @param controller the current session controller.
   * @param componentId the component hosting the object.
   * @param object the object to be accessed.
   * @return true if access is granted - false otherwise.
   * @throws Exception
   */
  public boolean isUserAuthorized(MainSessionController controller, String componentId, T object) 
      throws Exception ;

}