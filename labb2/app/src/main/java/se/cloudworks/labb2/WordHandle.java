package se.cloudworks.labb2;

import java.io.IOException;
import java.util.List;

public interface WordHandle {

    String getWord(MainActivity view) throws IOException;
    String randomizeWord(List list);
}
