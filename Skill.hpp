#ifndef SKILL_HPP
#define SKILL_HPP

#include <vector>
#include <string.h>
#include "Exception.hpp"
#include "Element.hpp"

using namespace std;

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
