#ifndef SKILL_ITEM_HPP
#define SKILL_ITEM_HPP

#include "Skill.hpp"
#include <stdlib.h>
#include <iostream>
using namespace std;

class SkillItem
{
	private:
		Skill containedSkill;
		string itemName;
		
	public:
		SkillItem(const Skill& s, string itemName);
		SkillItem(const SkillItem& si);
		
		string getName() const;
		Skill getContainedSkill() const;
		friend ostream& operator << (ostream& out, const SkillItem& si);
		bool operator==(const SkillItem& si);
};

extern vector<SkillItem> listOfSkillItem;
SkillItem& getSkillItemByName(string name);
SkillItem randomSkillItem(vector<Element> elements);

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

extern SkillItem TM01;
extern SkillItem TM02;
extern SkillItem TM03;
extern SkillItem TM04;
extern SkillItem TM05;
extern SkillItem TM06;
extern SkillItem TM07;
extern SkillItem TM08;
extern SkillItem TM09;
extern SkillItem TM10;
extern SkillItem TM11;
extern SkillItem TM12;
extern SkillItem TM13;
extern SkillItem TM14;
extern SkillItem TM15;
extern SkillItem TM16;
extern SkillItem TM17;
extern SkillItem TM18;
extern SkillItem TM19;
extern SkillItem TM20;
extern SkillItem TM21;
extern SkillItem TM22;
extern SkillItem TM23;
extern SkillItem TM24;
extern SkillItem TM25;
extern SkillItem TM26;

#endif
