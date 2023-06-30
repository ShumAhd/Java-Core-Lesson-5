import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    int[] gameBoard = new int[]{2, 1, 0, 2, 0, 2, 0, 1, 3};

    int encodedBoard = 0;

    for (int i = 0; i < gameBoard.length; i++) {
      encodedBoard = encodedBoard << 2;
      encodedBoard = encodedBoard | gameBoard[i];
      System.out.println(
          gameBoard[i] + " " + (Integer.toBinaryString(encodedBoard)) + " " + (encodedBoard));
      //System.out.println((Integer.toBinaryString(encodedBoard))+ " " + (encodedBoard));

    }
    DataOutputStream dos = new DataOutputStream(new FileOutputStream("gameBoard.bin"));
    dos.writeInt(encodedBoard);
    dos.close();

    DataInputStream dis = new DataInputStream(new FileInputStream("gameBoard.bin"));
    int readBoard = dis.readInt();
    dis.close();
    System.out.print(readBoard + " ");
    System.out.println(Integer.toBinaryString(readBoard));

    for (int i = 0; i < gameBoard.length; i++) {
      int cellState = readBoard & 3;
      readBoard = readBoard >> 2;

      char cellSymbol;
      switch (cellState) {
        case 0:
          cellSymbol = '.';
          break;
        case 1:
          cellSymbol = 'x';
          break;
        case 2:
          cellSymbol = 'o';
          break;
        default:
          cellSymbol = '?'; // неизвестное состояние ячейки
          break;
      }
      System.out.print(cellSymbol + " ");
      if ((i + 1) % 3 == 0) {
        System.out.println();
      }
    }
  }
}
