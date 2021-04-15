import java.util.ArrayList;

public class MainSpecies {
    public static void main(String[] args){
        ArrayList<Element> e1 = new ArrayList<>();
        e1.add(Element.FIRE);
        e1.add(Element.WATER);
        Skill s1 = new Skill("Panas", 2000, Element.FIRE);
        ArrayList<String> r1 = new ArrayList<>();
        r1.add("BAMBANG");
        r1.add("KENTANG");
        Species x = new Species("spesies_1",e1, s1,r1);

        ArrayList<Element> e2 = new ArrayList<>();
        e2.add(Element.WATER);
        e2.add(Element.FIRE);
        Skill s2 = new Skill("Dingin", 2000, Element.WATER);
        ArrayList<String> r2 = new ArrayList<>();
        r2.add("APA");
        r2.add("HAYO");
        Species y = new Species("spesies_2",e2, s2,r2);

        boolean res = Util.isElementSame(e1,e2);
        if(res){
            System.out.println("ELEMENT SAMA!");
        }else{
            System.out.println("ELEMENT BEDA!");
        }
    }
}
