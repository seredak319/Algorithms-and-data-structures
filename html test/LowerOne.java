
public class LowerOne {
  public static void main(String[] args) {
    if(args.length != 1){
      System.out.println("Bad amount of arguments");
      return;
    }
    if(args[0].length() > 6){
      System.out.println("Bad length of argument");
      return;
    }

    char[] result = new char[6];
    int i = 0;

    for (char x: args[0].toCharArray()) {
      result[i] = (char) ((int)x -1);
      i++;
    }

    System.out.println("Your result is:\t\t" + String.valueOf(result));
  }
}
