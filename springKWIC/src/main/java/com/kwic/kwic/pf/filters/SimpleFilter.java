package com.kwic.kwic.pf.filters;

import com.kwic.kwic.pf.Interfaces.FilterInterface;
import com.kwic.kwic.pf.context.Context;

import java.io.IOException;

public abstract class SimpleFilter implements FilterInterface {
    @Override
    //todo: make custom alterations for other filter classes
    public abstract void run(Context context) throws IOException;
}
