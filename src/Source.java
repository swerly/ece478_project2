import part_two.PartTwo;
import part_one.PartOne;
import part_three.PartThree;

/**
 * Created by sethw on 10/31/2016.
 */
public class Source {

    public static void main(String[] args){
        //PartOne p1 = new PartOne();

        //p1.execute();

        PartTwo p2 = new PartTwo();
        p2.start();

        PartThree p3 = new PartThree();
        p3.run(p2.getNodes());


    }
}
