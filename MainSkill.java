public class MainSkill {
    public static void main(String[] args) {
        Skill s1 = new Skill("Best", 2000, Element.FIRE, Element.GROUND);

        SkillItem si1 = new SkillItem("TM00", s1);

        System.out.println(s1);
        System.out.println();
        System.out.println(si1);
    }
}
