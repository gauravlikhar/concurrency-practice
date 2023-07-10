import java.util.Scanner;

/**
 * @author gaurav.likhar
 * @date 19/06/23
 * @project_name Practice
 **/

public final class Yukti {
    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "abcd";
        String s3 = new String("abcd");
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());
        System.out.println(s1.contentEquals(s2) + " " + s2.equals(s3));
    }
}
