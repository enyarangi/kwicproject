package com.kwic.kwic.pf.filters;



import com.kwic.kwic.pf.context.Context;

import java.io.IOException;
import java.util.ArrayList;

public class NoiseWordFilter extends  SimpleFilter {
    String noiseWords[] = {"a ", "an ", "the ", "and ", "or ", "of ", "to ", "be ", "is ", "in ", "out ", "by ", "as ", "at ", "off "};
    ArrayList<String> result = new ArrayList<String>();

    @Override
    public void run(Context context) throws IOException {
        boolean found = context.findParameterByKey("key");
        if (found == true) {
            try {
                result.clear();
                ArrayList<String> input = context.getParameter("key");
                for (String string : input) {
                    Boolean noiseWordPresent = false;
                    string = string.toLowerCase();
                    for (String noiseWord : noiseWords) {
                        if (string.startsWith(noiseWord)) {
                            noiseWordPresent = true;
                        }
                    }
                    if (!noiseWordPresent) {
                        result.add(string);
                    }
                }
                context.putParameter("key", result);
            } catch (Error e) {
                System.out.println(e.getCause() + "\n" + e.getMessage());
            }
        }
    }
}

