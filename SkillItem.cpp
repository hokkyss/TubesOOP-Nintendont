#include "Element.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include<iostream>
#include<bits/stdc++.h>
using namespace std;

SkillItem :: SkillItem(const Skill& s, string itemName) : containedSkill(s)
{
	this->itemName = itemName;
	listOfSkillItem.push_back(*this);
}

SkillItem :: SkillItem(const SkillItem& si) : containedSkill(si.getContainedSkill())
{
	this->itemName = si.getItemName();
}

ostream& operator << (ostream& out, const SkillItem& si)
{
	out << si.itemName << ": ";
	out << si.containedSkill;
	return out;
}

bool SkillItem :: operator == (const SkillItem& si)
{
	return (this->itemName == si.itemName) && (this->containedSkill == si.containedSkill);
}


string SkillItem :: getItemName() const
{
	return this->itemName;
}

Skill SkillItem :: getContainedSkill() const
{
	return this->containedSkill;
}

SkillItem& getSkillItemByName(string name)
{
	for(int i = 0; i < listOfSkillItem.size(); i++)
	{
		if(listOfSkillItem[i].getItemName() == name) return listOfSkillItem[i];
	}
	throw ItemNotFoundException();
}

SkillItem& randomSkillItem(vector<Element> elements)
{
	vector<SkillItem> candidates;
	int listOfSkillItemSize = listOfSkillItem.size();
	
	bool isCandidate;
	for(int i = 0; i < listOfSkillItemSize; i++)
	{
		isCandidate = true;
		for(int j = 0; j < elements.size(); j++)
		{
			if(listOfSkillItem[i].getContainedSkill().isLearntBy(elements[j])) continue;
			
			isCandidate = false;
		}
		
		if(isCandidate)
		{
			candidates.push_back(listOfSkillItem[i]);
		}
	}
	
	int randomIndex = rand() % candidates.size();
	return candidates[randomIndex];
}

vector<Skill> listOfSkill;
vector<SkillItem> listOfSkillItem;

Skill Tackle("Tackle", 50, Fire, Ice, Ground, Electric, Water);
Skill BodySlam("Body Slam", 90, Fire, Ice, Ground, Electric, Water);
Skill DoubleEdge("Double Edge", 120, Fire, Ice, Ground, Electric, Water);
Skill GigaImpact("Giga Impact", 150, Fire, Ice, Ground, Electric, Water);
Skill FirePledge("Fire Pledge", 50, Fire);
Skill FlameThrower("Flamethrower", 90, Fire);
Skill FireBlast("Fire Blast", 120, Fire);
Skill BlastBurn("Blast Burn", 150, Fire);
Skill Avalanche("Avalanche", 60, Ice);
Skill IceBeam("Ice Beam", 90, Ice);
Skill Blizzard("Blizzard", 120, Ice);
Skill SubzeroSlammer("Subzero Slammer", 140, Ice);
Skill Bulldoze("Bulldoze", 60, Ground);
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

SkillItem TM01(GigaImpact, "TM01");
SkillItem TM02(BlastBurn, "TM02");
SkillItem TM03(SubzeroSlammer, "TM03");
SkillItem TM04(TectonicRage, "TM04");
SkillItem TM05(GigavoltHavoc, "TM05");
SkillItem TM06(HydroCannon, "TM06");
SkillItem TM07(IceHammer, "TM07");
SkillItem TM08(MaxHailstorm, "TM08");
SkillItem TM09(FreezeJolt, "TM09");
SkillItem TM10(FreezingFlame, "TM10");
SkillItem TM11(Thunder, "TM11");
SkillItem TM12(Mudflood, "TM12");
SkillItem TM13(SteamBlast, "TM13");
SkillItem TM14(ThunderBlast, "TM14");
SkillItem TM15(ShockAndBurn, "TM15");
SkillItem TM16(Eruption, "TM16");
SkillItem TM17(WeatherBall, "TM17");
SkillItem TM18(TriAttack, "TM18");
SkillItem TM19(EarthPower, "TM19");
SkillItem TM20(Korslet, "TM20");
SkillItem TM21(MeltTheGround, "TM21");
SkillItem TM22(ShockTheFlame, "TM22");
SkillItem TM23(FreezeDry, "TM23");
SkillItem TM24(Electrolysis, "TM24");
SkillItem TM25(ColdRefrigerator, "TM25");
SkillItem TM26(ContradictingShock, "TM26");
