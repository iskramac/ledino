package com.jeefix.home.ledino.common.helper;

import com.jeefix.home.ledino.common.enums.LedChannel;
import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import com.jeefix.home.ledino.model.LedColor;

/**
 * @author mais
 *
 */
public class ColorHelper {

  /**
   * Extracts specified channel from color wrapper
   * 
   */
  public static int getChannelValue(LedColor color, LedChannel channel) {
    switch (channel) {
      case BLUE: {
        return color.getBlue();
      }
      case GREEN: {
        return color.getGreen();
      }
      case RED: {
        return color.getRed();
      }
      default: {
        throw new LedinoRuntimeException(String.format("Unhandled channel:", channel));
      }
    }
  }

  public static void setChannelValue(LedColor color, LedChannel channel, int value) {
    switch (channel) {
      case BLUE: {
        color.setBlue(value);
      }
      case GREEN: {
        color.setGreen(value);
      }
      case RED: {
        color.setRed(value);
      }
      default: {
        throw new LedinoRuntimeException(String.format("Unhandled channel:", channel));
      }
    }

  }

}
