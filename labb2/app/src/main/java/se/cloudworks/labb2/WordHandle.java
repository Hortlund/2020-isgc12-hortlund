package se.cloudworks.labb2;

import java.io.IOException;
import java.util.List;

public interface WordHandle {
    //the interface just makes this more complex, but separations of concerns, just trying to get a good grade.
    //simple functions that the wordhandler needs.
    String getWord(MainActivity view) throws IOException;
    String randomizeWord(List list);
}
