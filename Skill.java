import java.util.ArrayList;

public class Skill implements Comparable<Skill> {
    // atribut Skill
    public final String skillName;
    public final int basePower;
    private byte masteryLevel;
    public final ArrayList<Element> learnableBy;

    // menampung semua Skill yang dienumerasi di bawah
    public static ArrayList<Skill> listOfSkill = new ArrayList<>();

    // constructor di bawah ini diprivate agar tidak ada Skill baru oleh pengguna
    private Skill(String skillName, int basePower, ArrayList<Element> e) {
        this.skillName = skillName;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.learnableBy = e;
        Skill.listOfSkill.add(this);
    }

    // hanya copy constructor yang boleh dipakai
    public Skill(Skill s) {
        this.skillName = s.skillName;
        this.basePower = s.basePower;
        this.masteryLevel = s.getMasteryLevel();
        this.learnableBy = new ArrayList<>(s.learnableBy);
        this.learnableBy.sort(Element.comparator);
    }

    public void increaseMasteryLevel() {
        if (this.masteryLevel == 3)
            return;
        this.masteryLevel++;
    }

    public void setMasteryLevel(byte masteryLevel) {
        this.masteryLevel = masteryLevel;

        if (this.masteryLevel <= 1)
            this.masteryLevel = 1;
        if (this.masteryLevel >= 3)
            this.masteryLevel = 3;
    }

    public byte getMasteryLevel() {
        return this.masteryLevel;
    }

    public String toString() {
        String s = "";
        s = s + this.skillName + "\n";
        s = s + "Power : " + this.basePower + "\n";
        s = s + "Mastery : " + this.masteryLevel;
        return s;
    }

    public boolean equals(Skill s) {
        return this.skillName.equals(s.skillName);
    }

    public static Skill getBySkillName(String skillName) {
        for (Skill s : Skill.listOfSkill) {
            if (s.skillName.equals(skillName))
                return s;
        }
        return null;
    }

    public int compareTo(Skill s) {
        return this.basePower - s.basePower;
    }

    // daftar Skill (dienumerasi)
    public static final Skill TACKLE = new Skill("Tackle", 50,
            Element.constructElements("FIRE, ICE, GROUND, ELECTRIC, WATER"));
    public static final Skill BODYSLAM = new Skill("Body Slam", 90,
            Element.constructElements("FIRE, ICE, GROUND, ELECTRIC, WATER"));
    public static final Skill DOUBLEEDGE = new Skill("Double Edge", 120,
            Element.constructElements("FIRE, ICE, GROUND, ELECTRIC, WATER"));
    public static final Skill GIGAIMPACT = new Skill("Giga Impact", 150,
            Element.constructElements("FIRE, ICE, GROUND, ELECTRIC, WATER"));
    public static final Skill FIREPLEDGE = new Skill("Fire Pledge", 50, Element.constructElements("FIRE"));
    public static final Skill FLAMETHROWER = new Skill("Flamethrower", 90, Element.constructElements("FIRE"));
    public static final Skill FIREBLAST = new Skill("Fire Blast", 120, Element.constructElements("FIRE"));
    public static final Skill BLASTBURN = new Skill("Blast Burn", 150, Element.constructElements("FIRE"));
    public static final Skill AVALANCHE = new Skill("Avalanche", 60, Element.constructElements("ICE"));
    public static final Skill ICEBEAM = new Skill("Ice Beam", 90, Element.constructElements("ICE"));
    public static final Skill BLIZZARD = new Skill("Blizzard", 120, Element.constructElements("ICE"));
    public static final Skill SUBZEROSLAMMER = new Skill("Subzero Slammer", 140, Element.constructElements("ICE"));
    public static final Skill BULLDOZE = new Skill("BullDoze", 60, Element.constructElements("GROUND"));
    public static final Skill THOUSANDARROWS = new Skill("Thousand Arrows", 90, Element.constructElements("GROUND"));
    public static final Skill EARTHQUAKE = new Skill("Earthquake", 100, Element.constructElements("GROUND"));
    public static final Skill TECTONICRAGE = new Skill("Tectonic Rage", 160, Element.constructElements("GROUND"));
    public static final Skill SHOCKWAVE = new Skill("Shock Wave", 60, Element.constructElements("ELECTRIC"));
    public static final Skill THUNDERBOLT = new Skill("Thunderbolt", 90, Element.constructElements("ELECTRIC"));
    public static final Skill VOLTTACKLE = new Skill("Volt Tackle", 120, Element.constructElements("ELECTRIC"));
    public static final Skill GIGAVOLTHAVOC = new Skill("Gigavolt Havoc", 140, Element.constructElements("ELECTRIC"));
    public static final Skill WATERPLEDGE = new Skill("Water Pledge", 50, Element.constructElements("WATER"));
    public static final Skill SURF = new Skill("Surf", 90, Element.constructElements("WATER"));
    public static final Skill HYDROPUMP = new Skill("Hydro Pump", 120, Element.constructElements("WATER"));
    public static final Skill HYDROCANNON = new Skill("Hydro Cannon", 150, Element.constructElements("WATER"));
    public static final Skill LIQUIDATION = new Skill("Liquidation", 85, Element.constructElements("WATER, ICE"));
    public static final Skill ICEHAMMER = new Skill("Ice Hammer", 100, Element.constructElements("ICE, WATER"));
    public static final Skill MAXHAILSTORM = new Skill("Max Hailstorm", 110, Element.constructElements("GROUND, ICE"));
    public static final Skill ICICLESPEAR = new Skill("Icicle Spear", 75, Element.constructElements("ICE, GROUND"));
    public static final Skill FREEZEJOLT = new Skill("Freeze Jolt", 105, Element.constructElements("ICE, ELECTRIC"));
    public static final Skill QUADRUPLEAXEL = new Skill("Quadruple Axel", 80,
            Element.constructElements("ELECTRIC, ICE"));
    public static final Skill FREEZINGFLAME = new Skill("Freezing Flame", 110, Element.constructElements("ICE, FIRE"));
    public static final Skill BURNINGCHILL = new Skill("Burning Chill", 75, Element.constructElements("FIRE, ICE"));
    public static final Skill THUNDER = new Skill("Thunder", 110, Element.constructElements("ELECTRIC, WATER"));
    public static final Skill STORM = new Skill("Storm", 75, Element.constructElements("WATER, ELECTRIC"));
    public static final Skill MUDFLOOD = new Skill("Mudflood", 95, Element.constructElements("GROUND, WATER"));
    public static final Skill MUDDYWATER = new Skill("Muddy Water", 90, Element.constructElements("WATER, GROUND"));
    public static final Skill STEAMBLAST = new Skill("Steam Blast", 105, Element.constructElements("WATER, WATER"));
    public static final Skill STEAMBOMB = new Skill("Steam Bomb", 80, Element.constructElements("FIRE, WATER"));
    public static final Skill THUNDERBLAST = new Skill("Thunder Blast", 110,
            Element.constructElements("ELECTRIC, GROUND"));
    public static final Skill EARTHYSHOCK = new Skill("Earthy Shock", 75,
            Element.constructElements("GROUND, ELECTRIC"));
    public static final Skill SHOCKANDBURN = new Skill("Shock and Burn", 100,
            Element.constructElements("ELECTRIC, FIRE"));
    public static final Skill BURNANDSHOCK = new Skill("Burn and Shock", 85,
            Element.constructElements("FIRE, ELECTRIC"));
    public static final Skill ERUPTION = new Skill("Eruption", 100, Element.constructElements("FIRE, GROUND"));
    public static final Skill LAVAPLUME = new Skill("Lava Plume", 85, Element.constructElements("FIRE, GROUND"));
    public static final Skill WEATHERBALL = new Skill("Weather Ball", 90,
            Element.constructElements("FIRE, WATER, ICE"));
    public static final Skill TRIATTACK = new Skill("Tri Attack", 90, Element.constructElements("FIRE, ICE, ELECTRIC"));
    public static final Skill EARTHPOWER = new Skill("Earth Power", 90,
            Element.constructElements("FIRE, GROUND, WATER"));
    public static final Skill KORSLET = new Skill("Korslet", 90, Element.constructElements("FIRE, ELECTRIC, WATER"));
    public static final Skill MELTTHEGROUND = new Skill("Melt the Ground", 90,
            Element.constructElements("FIRE, GROUND, ICE"));
    public static final Skill SHOCKTHEFLAME = new Skill("Shock the Flame", 90,
            Element.constructElements("FIRE, GROUND, ELECTRIC"));
    public static final Skill FREEZEDRY = new Skill("Freeze Dry", 90, Element.constructElements("ICE, WATER, GROUND"));
    public static final Skill ELECTROLYSIS = new Skill("Electrolysis", 90,
            Element.constructElements("ICE, WATER, ELECTRIC"));
    public static final Skill COLDREFRIGERATOR = new Skill("Cold Refrigerator", 90,
            Element.constructElements("ICE, ELECTRIC, GROUND"));
    public static final Skill CONTRADICTINGSHOCK = new Skill("Contradicting Shock", 90,
            Element.constructElements("GROUND, WATER, ELECTRIC"));
    public static final Skill GODLYFART = new Skill("Godly Fart", 90,
            Element.constructElements("FIRE, ICE, GROUND, ELECTRIC, WATER"));

}
