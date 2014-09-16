package com.jeefix.home.ledino.common.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;

public class ObjectCloner {

  private static final String ERROR_MSG = "Unable to create deep copy of object";

  private ObjectCloner() {}

  // returns a deep copy of an object
  @SuppressWarnings("unchecked")
  public static <T> T deepCopy(T oldObj) {
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      oos = new ObjectOutputStream(bos);
      // serialize and pass the object
      oos.writeObject(oldObj); // C
      oos.flush(); // D
      ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray()); // E
      ois = new ObjectInputStream(bin); // F
      // return the new object
      return (T) ois.readObject(); // G
    } catch (Exception e) {
      throw new LedinoRuntimeException(ERROR_MSG, e);
    } finally {
      try {
        if (oos != null) {
          oos.close();
        }
      } catch (IOException e) {
        throw new LedinoRuntimeException(ERROR_MSG, e);
      }
      try {
        if (ois != null) {
          ois.close();
        }
      } catch (IOException e) {
        throw new LedinoRuntimeException(ERROR_MSG, e);
      }
    }
  }

}
