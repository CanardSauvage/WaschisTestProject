package de.waschnick.challenge.ideas;

import java.util.ArrayList;
import java.util.Collection;

/**
 * JAVA:
 *
 * JavaScript:
 *
 * Python: https://github.com/FireFart/HashCollision-DOS-POC/blob/master/HashtablePOC.py
 */
public class HashCollissionGenerator {

    private static final int SIZE = 30;

    private static final String[] base = new String[]{"11", "0P"};

    private static Collection<String> generateHashCodeCollissions(int count) {
        Collection<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            int j = i;
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k <= SIZE; k++) {
                sb.append(base[j % 2]);
                j /= 2;
            }
            String s = sb.toString();
            result.add(s);
        }
        return result;
    }
}
