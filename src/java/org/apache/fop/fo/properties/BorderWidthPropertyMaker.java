/*
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id$ */

package org.apache.fop.fo.properties;

import org.apache.fop.apps.FOPException;
import org.apache.fop.fo.Constants;
import org.apache.fop.fo.PropertyList;

/**
 * This subclass of LengthProperty.Maker handles the special treatment of 
 * border width described in 7.7.20.
 */
public class BorderWidthPropertyMaker extends LengthProperty.Maker {
    int borderStyleId = 0;    
    
    /**
     * Create a length property which check the value of the border-*-style
     * property and return a length of 0 when the style is "none". 
     * @param propId the border-*-width of the property.
     */
    public BorderWidthPropertyMaker(int propId) {
        super(propId);
    }
    
    /**
     * Set the propId of the style property for the same side.
     * @param borderStyleId
     */
    public void setBorderStyleId(int borderStyleId) {
        this.borderStyleId = borderStyleId;
    }

    /**
     * Check the value of the style property and return a length of 0 when
     * the style is NONE.
     * @see org.apache.fop.fo.properties.PropertyMaker#get(int, PropertyList, boolean, boolean)
     */
   
    public Property get(int subpropId, PropertyList propertyList,
                        boolean bTryInherit, boolean bTryDefault)
        throws FOPException
    {
        Property p = super.get(subpropId, propertyList,
                               bTryInherit, bTryDefault);

        // Calculate the values as described in 7.7.20.
        Property style = propertyList.get(borderStyleId);
        if (style.getEnum() == Constants.NONE) {
            return new FixedLength(0);
        }
        return p;
    }
}
