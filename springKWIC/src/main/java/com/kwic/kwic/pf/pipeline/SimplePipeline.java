package com.kwic.kwic.pf.pipeline;


import com.kwic.kwic.pf.context.Context;
import com.kwic.kwic.pf.filters.SimpleFilter;
import org.springframework.util.StopWatch;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SimplePipeline extends PipedInputStream {
    public InputStream from = new PipedInputStream();
    public OutputStream to = new PipedOutputStream();
    public List<SimpleFilter> linkedFilters = new ArrayList<>();

    public Object execute(final Context context){
        try {
            StopWatch stopWatch = new StopWatch();
            long startTime = System.nanoTime();
            stopWatch.start();
            for (SimpleFilter filter : linkedFilters) {
                filter.run(context);
            }

            stopWatch.stop();
            long time = stopWatch.getLastTaskTimeMillis();
            long endTime = System.nanoTime();
            long timeElapsed = endTime-startTime;
            System.out.println("Time: " + timeElapsed);
            System.out.println(time);
            return context.getParameter("key");
        }
        catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }

    public String listToString(List<String> list){
        String output = new String();
        for(String string: list){
            output = output.concat(string);
        }
        return output;
    }

    public SimplePipeline() {
    }
}

