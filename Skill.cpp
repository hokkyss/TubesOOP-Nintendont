#include "Skill.hpp"

using namespace std;

Skill :: Skill(string name, int basePower)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 0;
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 1;
	this->elements.push_back(e);
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 2;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2, Element e3)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 3;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2, Element e3, Element e4)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 4;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
	this->elements.push_back(e4);
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2, Element e3, Element e4, Element e5)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 5;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
	this->elements.push_back(e4);
	this->elements.push_back(e5);
	listOfSkill.push_back(*this);
}

Skill :: Skill(const Skill& s) : skillName(s.getName())
{
	this->basePower = s.getBasePower();
	this->masteryLevel = s.getMasteryLevel();
	this->numOfElements = s.getNumOfElements();
	for(int i = 0; i < this->numOfElements; i++)
	{
		this->elements.push_back(s.getElement(i));
	}
}

void Skill :: operator=(const Skill& s)
{
	this->skillName = s.getName();
	this->basePower = s.getBasePower();
	this->masteryLevel = s.getMasteryLevel();
	this->numOfElements = s.getNumOfElements();
	for(int i = 0; i < this->numOfElements; i++)
	{
		this->elements.push_back(s.getElement(i));
	}
}

bool Skill :: operator==(const Skill& s)
{
	return this->skillName == s.getName();
}

Skill :: ~Skill()
{
}

ostream& operator<<(ostream& out, const Skill& s)
{
	out << s.getName() << endl;
	out << "Base Power   : " << s.getBasePower() << endl;
	out << "Mastery Level: " << s.getMasteryLevel() << endl;
	/*
	out << "Can be learnt by " << s.getNumOfElements() << " element(s):" << endl;
	
	for(int i = 0; i < s.getNumOfElements(); i++)
	{
		out << i + 1 << ". " << s.getElement(i) << endl;
	}
	*/
	return out;
}

string Skill :: getName() const
{
	return this->skillName;
}
		
int Skill :: getBasePower() const
{
	return this->basePower;
}

int Skill :: getMasteryLevel() const
{
	return this->masteryLevel;
}

void Skill :: setMasteryLevel(int newMasteryLevel)
{
	this->masteryLevel = newMasteryLevel;
}

int Skill :: getNumOfElements() const
{
	return this->numOfElements;
}


vector<Element> Skill :: getElements() const {
	return this->elements;
}
		
bool Skill :: isLearntBy(Element e) const
{
	for(int i = 0; i < this->numOfElements; i++)
	{
		if(this->elements[i] == e) return true;
	}
	return false;
}

Element Skill :: getElement(int index) const
{
	if(index >= this->numOfElements || index < 0)
	{
		throw IndexInvalidException();
	}
	
	return this->elements[index];
}

Skill& getSkillByName(string name)
{
	for(int i = 0; i < listOfSkill.size(); i++)
	{
		if(listOfSkill[i].getName() == name) return listOfSkill[i];
	}
	throw ItemNotFoundException();
}

vector<Skill> listOfSkill;

Skill Tackle("Tackle", 50, Fire, Ice, Ground, Electric, Water);
Skill BodySlam("Body Slam", 90, Fire, Ice, Ground, Electric, Water);
Skill DoubleEdge("Double Edge", 120, Fire, Ice, Ground, Electric, Water);
Skill GigaImpact("Giga Impact", 150, Fire, Ice, Ground, Electric, Water);
Skill FirePledge("Fire Pledge", 50, Fire);
Skill FlameThrower("Flamethrower", 200, Fire);
Skill FireBlast("Fire Blast", 120, Fire);
Skill BlastBurn("Blast Burn", 150, Fire);
Skill Avalanche("Avalanche", 80, Ice);
Skill IceBeam("Ice Beam", 50, Ice);
Skill Blizzard("Blizzard", 120, Ice);
Skill SubzeroSlammer("Subzero Slammer", 140, Ice);
Skill BullDoze("BullDoze", 60, Ground);
Skill ThousandArrows("Thousand Arrows", 90, Ground);
Skill Earthquake("Earthquake", 100, Ground);
Skill TectonicRage("Tectonic Rage", 160, Ground);
Skill ShockWave("Shock Wave", 60, Electric);
Skill Thunderbolt("Thunderbolt", 90, Electric);
Skill VoltTackle("Volt Tackle", 120, Electric);
Skill GigavoltHavoc("Gigavolt Havoc", 160, Electric);
Skill WaterPledge("Water Pledge", 50, Water);
Skill Surf("Surf", 90, Water);
Skill HydroPump("Hydro Pump", 120, Water);
Skill HydroCannon("Hydro Cannon", 150, Water);

Skill Liquidation("Liquidation", 85, Water, Ice);
Skill IceHammer("Ice Hammer", 100, Ice, Water);
Skill MaxHailstorm("Max Hailstorm", 110, Ground, Ice);
Skill IcicleSpear("Icicle Spear", 75, Ice, Ground);
Skill FreezeJolt("Freeze Jolt", 105, Ice, Electric);
Skill QuadrupleAxel("Quadruple Axel", 80, Electric, Ice);
Skill FreezingFlame("Freezing Flame", 110, Ice, Fire);
Skill BurningChill("Burning Chill", 75, Fire, Ice);
Skill Thunder("Thunder", 110, Electric, Water);
Skill Storm("Storm", 75, Water, Electric);
Skill Mudflood("Mudflood", 95, Ground, Water);
Skill MuddyWater("Muddy Water", 90, Water, Ground);
Skill SteamBlast("Steam Blast", 105, Water, Fire);
Skill SteamBomb("Steam Bomb", 80, Fire, Water);
Skill ThunderBlast("Thunder Blast", 110, Electric, Ground);
Skill EarthyShock("Earthy Shock", 75, Ground, Electric);
Skill ShockAndBurn("Shock and Burn", 100, Electric, Fire);
Skill BurnAndShock("Burn and Shock", 85, Fire, Electric);
Skill Eruption("Eruption", 100, Fire, Ground);
Skill LavaPlume("Lava Plume", 85, Fire, Ground);

Skill WeatherBall("Weather Ball", 90, Fire, Water, Ice);
Skill TriAttack("Tri Attack", 90, Fire, Ice, Electric);
Skill EarthPower("Earth Power", 90, Fire, Ground, Water);
Skill Korslet("Korslet", 90, Fire, Electric, Water);
Skill MeltTheGround("Melt the Ground", 90, Fire, Ground, Ice);
Skill ShockTheFlame("Shock the Flame", 90, Fire, Ground, Electric);
Skill FreezeDry("Freeze Dry", 90, Ice, Water, Ground);
Skill Electrolysis("Electrolysis", 90, Ice, Water, Electric);
Skill ColdRefrigerator("Cold Refrigerator", 90, Ice, Electric, Ground);
Skill ContradictingShock("Contradicting Shock", 90, Ground, Water, Electric);
Skill GodlyFart("GodlyFart", 90, Fire, Ice, Ground, Electric, Water);
