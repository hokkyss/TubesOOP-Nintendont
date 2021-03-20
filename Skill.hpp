#ifndef SKILL_HPP
#define SKILL_HPP

#include<iostream>
#include<vector>
#include<exception>
#include "Element.hpp"
using namespace std;

class IndexInvalidException : exception
{
	const char* what() const throw()
	{
		return "Index invalid";
	}
};

class Skill
{
	private:
		int basePower;
		int masteryLevel;
		int numOfElements;
		vector<Element> elements;
	
	public:
		// skill bisa saja dipelajari oleh semua elemen
		Skill(int basePower, Element e);
		Skill(int basePower, Element e1, Element e2);
		Skill(int basePower, Element e1, Element e2, Element e3);
		Skill(int basePower, Element e1, Element e2, Element e3, Element e4);
		Skill(int basePower, Element e1, Element e2, Element e3, Element e4, Element e5);
		Skill(const Skill& s);
		void operator=(const Skill& s);
		~Skill();
		
		friend ostream& operator << (ostream& out, const Skill& s);
		
		int getMasteryLevel() const;
		void setMasteryLevel(int newMasteryLevel);
		
		int getBasePower() const;
		int getNumOfElements() const;
		
		// mengembalikan elemen ke-index
		// jika index "tidak diisi", akan ngethrow exception IndexOutOfRange
		Element getElement(int index) const;
};

// harus extern?
Skill Tackle(50, Fire, Ice, Ground, Electric, Water);
Skill BodySlam(90, Fire, Ice, Ground, Electric, Water);
Skill DoubleEdge(120, Fire, Ice, Ground, Electric, Water);
Skill GigaImpact(150, Fire, Ice, Ground, Electric, Water);
Skill FirePledge(50, Fire);
Skill FlameThrower(90, Fire);
Skill FireBlast(120, Fire);
Skill BlastBurn(150, Fire);
Skill Avalanche(60, Ice);
Skill IceBeam(90, Ice);
Skill Blizzard(120, Ice);
Skill SubzeroSlammer(140, Ice);
Skill Bulldoze(60, Ground);
Skill ThousandArrows(90, Ground);
Skill Earthquake(100, Ground);
Skill TectonicRage(160, Ground);
Skill ShockWave(60, Electric);
Skill Thunderbolt(90, Electric);
Skill VoltTackle(120, Electric);
Skill GigavoltHavoc(160, Electric);
Skill WaterPledge(50, Water);
Skill Surf(90, Water);
Skill HydroPump(120, Water);
Skill HydroCannon(150, Water);

Skill Liquidation(85, Water, Ice);
Skill IceHammer(100, Ice, Water);
Skill MaxHailstorm(110, Ground, Ice);
Skill IcicleSpear(75, Ice, Ground);
Skill FreezeJolt(105, Ice, Electric);
Skill QuadrupleAxel(80, Electric, Ice);
Skill FreezingFlame(110, Ice, Fire);
Skill BurningChill(75, Fire, Ice);
Skill Thunder(110, Electric, Water);
Skill Storm(75, Water, Electric);
Skill Mudflood(95, Ground, Water);
Skill MuddyWater(90, Water, Ground);
Skill SteamBlast(105, Water, Fire);
Skill SteamBomb(80, Fire, Water);
Skill ThunderBlast(110, Electric, Ground);
Skill EarthyShock(75, Ground, Electric);
Skill ShockAndBurn(100, Electric, Fire);
Skill BurnAndShock(85, Fire, Electric);
Skill Eruption(100, Fire, Ground);
Skill LavaPlume(85, Fire, Ground);

Skill WeatherBall(90, Fire, Water, Ice);
Skill TriAttack(90, Fire, Ice, Electric);
Skill EarthPower(90, Fire, Ground, Water);
Skill Korslet(90, Fire, Electric, Water);
Skill MeltTheGround(90, Fire, Ground, Ice);
Skill ShockTheFlame(90, Fire, Ground, Electric);
Skill FreezeDry(90, Ice, Water, Ground);
Skill ElectroLysis(90, Ice, Water, Electric);
Skill ColdRefrigerator(90, Ice, Electric, Ground);
Skill ContradictingShock(90, Ground, Water, Electric);

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
extern Skill ElectroLysis(90, Ice, Water, Electric);
extern Skill ColdRefrigerator(90, Ice, Electric, Ground);
extern Skill ContradictingShock(90, Ground, Water, Electric);
*/
#endif
