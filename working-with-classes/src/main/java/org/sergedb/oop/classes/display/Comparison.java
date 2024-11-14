package org.sergedb.oop.classes.display;

import java.util.List;

public class Comparison {

    private String compareDisplays(Display display1, Display display2) {
        int sizeComparison = Integer.compare(
            display1.width() * display1.height(),
            display2.width() * display2.height()
        );

        int sharpnessComparison = Float.compare(display1.ppi(), display2.ppi());

        String sizeResult = (sizeComparison == 0) ? "the same size as"
                                                  : (sizeComparison > 0) ? "larger than"
                                                                         : "smaller than";

        String sharpnessResult = (sharpnessComparison == 0) ? "the same sharpness as"
                                                            : (sharpnessComparison > 0) ? "sharper than"
                                                                                        : "less sharp than";

        if (sizeComparison == 0 && sharpnessComparison == 0) {
            return display1.model() + " is the same size and sharpness as " + display2.model();
        }

        return display1.model() + " is " + sizeResult + " and " + sharpnessResult + " " + display2.model();
    }

    public void compareDisplays(List<Display> displays) {
        for (int i = 0; i < displays.size() - 1; i++) {
            Display display1 = displays.get(i);
            Display display2 = displays.get(i + 1);
            System.out.println(compareDisplays(display1, display2));
        }
    }


}