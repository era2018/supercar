package com.era.supercar;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class DummyDataStream
{

    public InputStream getInputStream()
    {
        InputStream is = new ByteArrayInputStream("{\"type\":\"input\",\"timestamp\":1531506761624,\"percent\":35,\"absolute\":360,\"name\":\"amplitude\",\"payload\":{\"percent\":35,\"absolute\":360},\"from\":{\"device\":{\"id\":\"243c201f7957\",\"mac\":\"243c201f7957\"}}}".getBytes());
        //InputStream is = new ByteArrayInputStream("{}".getBytes());
        return is;
    }
}