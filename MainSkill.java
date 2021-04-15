public class MainSkill {
    public static void main(String[] args) {
        // HINDARI PAKE Skill s1 = Skill.SURF
        // Nanti masterynya bakal berubah untuk semua Skill
        // ITU ADALAH SKENARIO YANG TIDAK DIINGINKAN
        Skill s1 = new Skill(Skill.SURF);
        s1.increaseMasteryLevel();

        Skill s2 = Skill.SURF;
        System.out.println(s1);
        System.out.println(s2);
    }
}
