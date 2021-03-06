/**
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
 * FLOSS exception.  You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://repository.silverpeas.com/legal/licensing"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.silverpeas.workflow.engine.dataRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.silverpeas.form.fieldType.DateField;

/**
 * A read only DateField
 */
public class DateRoField extends DateField {
  private final String value;

  public DateRoField(Date value) {
    if (value != null)
      this.value = formatterBD.format(value);
    else
      this.value = null;
  }

  /**
   * Returns the string value of this field.
   */
  public String getStringValue() {
    return value;
  }

  /**
   * Changes nothing.
   */
  public void setStringValue(String value) {
  }

  /**
   * Returns true even if a set will changes nothing.
   */
  public boolean acceptStringValue(String value) {
    return true;
  }

  /**
   * Returns true.
   */
  public boolean isReadOnly() {
    return true;
  }

  private static final SimpleDateFormat formatterBD = new SimpleDateFormat(
      "yyyy/MM/dd");
}
