package org.sergedb.oop.classes.display;

import java.util.HashMap;
import java.util.Map;

public class ComparisonService {

public String compareDisplays(Display display1, Display display2) {
    int sizeComparison = Integer.compare(
        display1.getWidth() * display1.getHeight(),
        display2.getWidth() * display2.getHeight()
    );

    int sharpnessComparison = Float.compare(display1.getPpi(), display2.getPpi());

    String sizeResult = (sizeComparison == 0) ? "the same size as"
                                              : (sizeComparison > 0) ? "larger than"
                                                                     : "smaller than";

    String sharpnessResult = (sharpnessComparison == 0) ? "the same sharpness as"
                                                        : (sharpnessComparison > 0) ? "sharper than"
                                                                                    : "less sharp than";

    if (sizeComparison == 0 && sharpnessComparison == 0) {
        return display1.getModel() + " is the same size and sharpness as " + display2.getModel();
    }

    return display1.getModel() + " is " + sizeResult + " and " + sharpnessResult + " " + display2.getModel();
}


}