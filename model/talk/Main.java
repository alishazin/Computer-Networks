
import java.util.*;

class Main  {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            throw new Exception("One required argument.");
        }

        System.out.println(args[0]);

    }

};