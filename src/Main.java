import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome!");


        System.out.println("------------------------");

        System.out.println(LightOptimizers.generateLightGrid(16,3,4,13,0,0,3,true).toColoredString());

    }
}