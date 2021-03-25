#ifndef SKILL_HPP
#define SKILL_HPP

#include <vector>
#include <string.h>
#include <exception>
#include "Element.hpp"

using namespace std;

class IndexInvalidException : exception
{
	const char* what() const throw()
	{
		return "Index invalid";
	}
};

class ItemNotFoundException : exception
{
	const char* what() const throw()
	{
		return "Item not found!";
	}
};

class Skill
{
	private:
		string skillName;
		int basePower;
		int masteryLevel;
		int numOfElements;
		vector<Element> elements;
	
	public:
		// skill bisa saja dipelajari oleh semua elemen
		Skill(string skillName, int basePower);
		Skill(string skillName, int basePower, Element e);
		Skill(string skillName, int basePower, Element e1, Element e2);
		Skill(string skillName, int basePower, Element e1, Element e2, Element e3);
		Skill(string skillName, int basePower, Element e1, Element e2, Element e3, Element e4);
		Skill(string skillName, int basePower, Element e1, Element e2, Element e3, Element e4, Element e5);
		Skill(const Skill& s);
		void operator=(const Skill& s);
		bool operator==(const Skill& s);
		~Skill();
		
		friend ostream& operator << (ostream& out, const Skill& s);
		
		string getName() const;
		
		int getBasePower() const;
		
		int getMasteryLevel() const;
		void setMasteryLevel(int newMasteryLevel);
		
		int getNumOfElements() const;

		vector<Element> getElements() const;
		
		bool isLearntBy(Element e) const;
		
		// mengembalikan elemen ke-index
		// jika index "tidak diisi", akan ngethrow exception IndexOutOfRange
		Element getElement(int index) const;
};

extern vector<Skill> listOfSkill;
Skill& getSkillByName(string name);
// harus extern?
/*
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
*/
/*
extern Skill Tackle(50, Fire, Ice, Ground, Electric, Water);
extern Skill BodySlam(90, Fire, Ice, Ground, Electric, Water);
extern Skill DoubleEdge(120, Fire, Ice, Ground, Electric, Water);
extern Skill GigaImpact(150, Fire, Ice, Ground, Electric, Water);
extern Skill FirePledge(50, Fire);
extern Skill FlameThrower(90, Fire);
extern Skill FireBlast(120, Fire);
extern Skill BlastBurn(150, Fire);
extern Skill Avalanche(60, Ice);
extern Skill IceBeam(90, Ice);
extern Skill Blizzard(120, Ice);
extern Skill SubzeroSlammer(140, Ice);
extern Skill BullDoze(60, Ground);
extern Skill ThousandArrows(90, Ground);
extern Skill Earthquake(100, Ground);
extern Skill TectonicRage(160, Ground);
extern Skill ShockWave(60, Electric);
extern Skill Thunderbolt(90, Electric);
extern Skill VoltTackle(120, Electric);
extern Skill GigavoltHavoc(160, Electric);
extern Skill WaterPledge(50, Water);
extern Skill Surf(90, Water);
extern Skill HydroPump(120, Water);
extern Skill HydroCannon(150, Water);

extern Skill Liquidation(85, Water, Ice);
extern Skill IceHammer(100, Ice, Water);
extern Skill MaxHailstorm(110, Ground, Ice);
extern Skill IcicleSpear(75, Ice, Ground);
extern Skill FreezeJolt(105, Ice, Electric);
extern Skill QuadrupleAxel(80, Electric, Ice);
extern Skill FreezingFlame(110, Ice, Fire);
extern Skill BurningChill(75, Fire, Ice);
extern Skill Thunder(110, Electric, Water);
extern Skill Storm(75, Water, Electric);
extern Skill Mudflood(95, Ground, Water);
extern Skill MuddyWater(90, Water, Ground);
extern Skill SteamBlast(105, Water, Fire);
extern Skill SteamBomb(80, Fire, Water);
extern Skill ThunderBlast(110, Electric, Ground);
extern Skill EarthyShock(75, Ground, Electric);
extern Skill ShockAndBurn(100, Electric, Fire);
extern Skill BurnAndShock(85, Fire, Electric);
extern Skill Eruption(100, Fire, Ground);
extern Skill LavaPlume(85, Fire, Ground);

extern Skill WeatherBall(90, Fire, Water, Ice);
extern Skill TriAttack(90, Fire, Ice, Electric);
extern Skill EarthPower(90, Fire, Ground, Water);
extern Skill Korslet(90, Fire, Electric, Water);
extern Skill MeltTheGround(90, Fire, Ground, Ice);
extern Skill ShockTheFlame(90, Fire, Ground, Electric);
extern Skill FreezeDry(90, Ice, Water, Ground);
extern Skill Electrolysis(90, Ice, Water, Electric);
extern Skill ColdRefrigerator(90, Ice, Electric, Ground);
extern Skill ContradictingShock(90, Ground, Water, Electric);
*/

extern Skill Tackle;
extern Skill BodySlam;
extern Skill DoubleEdge;
extern Skill GigaImpact;
extern Skill FirePledge;
extern Skill FlameThrower;
extern Skill FireBlast;
extern Skill BlastBurn;
extern Skill Avalanche;
extern Skill IceBeam;
extern Skill Blizzard;
extern Skill SubzeroSlammer;
extern Skill BullDoze;
extern Skill ThousandArrows;
extern Skill Earthquake;
extern Skill TectonicRage;
extern Skill ShockWave;
extern Skill Thunderbolt;
extern Skill VoltTackle;
extern Skill GigavoltHavoc;
extern Skill WaterPledge;
extern Skill Surf;
extern Skill HydroPump;
extern Skill HydroCannon;

extern Skill Liquidation;
extern Skill IceHammer;
extern Skill MaxHailstorm;
extern Skill IcicleSpear;
extern Skill FreezeJolt;
extern Skill QuadrupleAxel;
extern Skill FreezingFlame;
extern Skill BurningChill;
extern Skill Thunder;
extern Skill Storm;
extern Skill Mudflood;
extern Skill MuddyWater;
extern Skill SteamBlast;
extern Skill SteamBomb;
extern Skill ThunderBlast;
extern Skill EarthyShock;
extern Skill ShockAndBurn;
extern Skill BurnAndShock;
extern Skill Eruption;
extern Skill LavaPlume;

extern Skill WeatherBall;
extern Skill TriAttack;
extern Skill EarthPower;
extern Skill Korslet;
extern Skill MeltTheGround;
extern Skill ShockTheFlame;
extern Skill FreezeDry;
extern Skill Electrolysis;
extern Skill ColdRefrigerator;
extern Skill ContradictingShock;
extern Skill GodlyFart;

#endif
