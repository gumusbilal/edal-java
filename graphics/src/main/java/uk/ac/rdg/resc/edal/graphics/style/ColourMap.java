/*******************************************************************************
 * Copyright (c) 2013 The University of Reading
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the University of Reading, nor the names of the
 *    authors or contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package uk.ac.rdg.resc.edal.graphics.style;

import java.awt.Color;

import uk.ac.rdg.resc.edal.graphics.style.util.ColourPalette;

public class ColourMap {
    // The colour to plot for values below the minimum. If null, then use the
    // lowest value in the palette
    private Color belowMinColour = null;
    // The colour to plot for values above the maximum. If null, then use the
    // highest value in the palette
    private Color aboveMaxColour = null;
    // The colour to plot for missing data
    private Color noDataColour = new Color(0, 0, 0, 0);
    
    // The colour to plot for missing data
    private Integer nColourBands = 254;
    
    private String paletteString = "default";
    
    private ColourPalette palette = null;
    
    public ColourMap(Color belowMinColour, Color aboveMaxColour, Color noDataColour,
            String palette, Integer nColourBands) {
        super();
        this.belowMinColour = belowMinColour;
        this.aboveMaxColour = aboveMaxColour;
        this.noDataColour = noDataColour;
        this.nColourBands = nColourBands;
        this.paletteString = palette;
    }

    public Color getColor(Number value) {
        if(palette == null) {
            palette = ColourPalette.fromString(paletteString, nColourBands);
        }
        if (value == null || Float.isNaN(value.floatValue())) {
            return noDataColour;
        }
        float val = value.floatValue();
        if (val < 0.0) {
            if(belowMinColour == null) {
                return palette.getColor(0f);
            }
            return belowMinColour;
        }
        if (val > 1.0) {
            if(aboveMaxColour == null) {
                return palette.getColor(1f);
            }
            return aboveMaxColour;
        }
        return palette.getColor(val);
    }
}
