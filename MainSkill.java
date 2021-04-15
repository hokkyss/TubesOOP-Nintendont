public class MainSkill {
    public static void main(String[] args) {
        // HINDARI PAKE Skill s1 = Skill.SURF
        // Nanti masterynya bakal berubah untuk semua Skill
        // ITU ADALAH SKENARIO YANG TIDAK DIINGINKAN
        Logger.print("SKENARIO YANG DIINGINKAN");
        Skill s1 = new Skill(Skill.SURF);
        s1.increaseMasteryLevel();
        Skill s2 = Skill.SURF;
        Logger.print(s1);
        Logger.print(s2);

        Logger.print("-------------------------\n-------------------------");
        Logger.print("SKENARIO YANG TIDAK DIINGINKAN");

        Skill s3 = Skill.FLAMETHROWER;
        s3.increaseMasteryLevel();
        Skill s4 = Skill.FLAMETHROWER;
        Logger.print(s3);
        Logger.print(s4);
        /*
         * Kalau cuma = , cuman nyimpan "address" atau "reference" dari objek, bukan
         * objek baru
         */
        /* Kalau pake copy constructor, dia ngeclone, jadi gak ngubah "sistem" */

        Logger.print(s3 + " AAA");
    }
}
