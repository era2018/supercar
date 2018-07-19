package com.era.supercar;

import java.io.InputStream;

class DummyDataStream
{

    public InputStream getInputStream()
    {
        DummyStream dum = new DummyStream();
        return dum;
    }
}