package com.era.supercar;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class DummyStream extends InputStream
{
  private ByteArrayInputStream is;

  public DummyStream()
  {
    this.is = new ByteArrayInputStream("{\"type\":\"input\",\"timestamp\":1531506761624,\"percent\":35,\"absolute\":360,\"name\":\"amplitude\",\"payload\":{\"percent\":35,\"absolute\":360},\"from\":{\"device\":{\"id\":\"243c201f7957\",\"mac\":\"243c201f7957\"}}}\n".getBytes());
  }
    
  @Override
  public int read() throws IOException {
    if( this.is.available() <= 0 )
    {
      this.is.reset();
    }
    return this.is.read();
  }
}