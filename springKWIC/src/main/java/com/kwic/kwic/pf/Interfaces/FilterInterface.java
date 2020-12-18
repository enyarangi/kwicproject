package com.kwic.kwic.pf.Interfaces;



import com.kwic.kwic.pf.context.Context;

import java.io.IOException;

public interface FilterInterface {
    void run(Context context) throws IOException;
}
