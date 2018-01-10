
package de.waschnick.notch;

import java.util.ArrayList;
import java.util.List;

import de.waschnick.lib.Bitmap;
import de.waschnick.lib.Point;

public class Program {

  static void Main(String[] args) {
    Bitmap bmp = Bitmap.loadFromClasspath("source.png");
    List<Integer> data = new ArrayList<>();
    Point lastPos = new Point(-1, -1);
    Point pos = new Point(251, 128);

    boolean hasNext = true;
    while (hasNext) {
      Integer value = getValueAtPoint(bmp, pos);
      data.add(value);

      int[] others = new int[8];
      for (int i = 0; i < others.length; i++) {
        others[i] = getValueAtPoint(bmp, getNextPos(pos, i));
      }

      hasNext = false;
      for (int i = 0; i < others.length; i++) {
        if (others[i] != 0) {
          Point nextPos = getNextPos(pos, i);
          if (nextPos.X != lastPos.X || nextPos.Y != lastPos.Y) {
            lastPos = pos;
            pos = nextPos;
            hasNext = true;
            break;
          }
        }
      }
    }
    // FIXME
    //    using (BinaryWriter bw = new BinaryWriter(File.OpenWrite("out-in.bin")))
    //    {
    //      for (int i = 0; i < data.Count; i++) { bw.Write(data[i]); }
    //    }
    //    using (BinaryWriter bw = new BinaryWriter(File.OpenWrite("in-out.bin")))
    //    {
    //      for (int i = data.Count; i-- > 0; ) { bw.Write(data[i]); }
    //    }
  }

  private static Point getNextPos(Point pos, int direction) {
    switch (direction) {
      case 0 :
        return new Point(pos.X, pos.Y - 1);
      case 1 :
        return new Point(pos.X + 1, pos.Y - 1);
      case 2 :
        return new Point(pos.X + 1, pos.Y);
      case 3 :
        return new Point(pos.X + 1, pos.Y + 1);
      case 4 :
        return new Point(pos.X, pos.Y + 1);
      case 5 :
        return new Point(pos.X - 1, pos.Y + 1);
      case 6 :
        return new Point(pos.X - 1, pos.Y);
      case 7 :
        return new Point(pos.X - 1, pos.Y - 1);
    }
    throw new RuntimeException();
  }

  private static Integer getValueAtPoint(Bitmap bmp, Point pos) {
    //    Color c = bmp.getPixel(pos.X, pos.Y);
    //    Debug.Assert(c.R == c.G && c.G == c.B);
    //    return c.R;
    return null; // FIXME hat früher ein byte zurück gegeben
  }
}
