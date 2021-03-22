#ifndef SKILL_ITEM_HPP
#define SKILL_ITEM_HPP

#include "Skill.hpp"
#include<iostream>
#include<vector>
using namespace std;

class SkillItem
{
	private:
		Skill containedSkill;
		string itemName;
	public:
		SkillItem(const Skill& s, string itemName);
		
		string getItemName();
		Skill& getContainedSkill();
		friend ostream& operator << (ostream& out, const SkillItem& si);
		bool operator==(const SkillItem& si);
};

extern vector<SkillItem> listOfSkillItem;
SkillItem& getSkillItemByName(string name);
// harus extern?
/*
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
*/
/*
extern SkillItem TM01(GigaImpact, "TM01");
extern SkillItem TM02(BlastBurn, "TM02");
extern SkillItem TM03(SubzeroSlammer, "TM03");
extern SkillItem TM04(TectonicRage, "TM04");
extern SkillItem TM05(GigavoltHavoc, "TM05");
extern SkillItem TM06(HydroCannon, "TM06");
extern SkillItem TM07(IceHammer, "TM07");
extern SkillItem TM08(MaxHailstorm, "TM08");
extern SkillItem TM09(FreezeJolt, "TM09");
extern SkillItem TM10(FreezingFlame, "TM10");
extern SkillItem TM11(Thunder, "TM11");
extern SkillItem TM12(Mudflood, "TM12");
extern SkillItem TM13(SteamBlast, "TM13");
extern SkillItem TM14(ThunderBlast, "TM14");
extern SkillItem TM15(ShockAndBurn, "TM15");
extern SkillItem TM16(Eruption, "TM16");
extern SkillItem TM17(WeatherBall, "TM17");
extern SkillItem TM18(TriAttack, "TM18");
extern SkillItem TM19(EarthPower, "TM19");
extern SkillItem TM20(Korslet, "TM20");
extern SkillItem TM21(MeltTheGround, "TM21");
extern SkillItem TM22(ShockTheFlame, "TM22");
extern SkillItem TM23(FreezeDry, "TM23");
extern SkillItem TM24(Electrolysis, "TM24");
extern SkillItem TM25(ColdRefrigerator, "TM25");
extern SkillItem TM26(ContradictingShock, "TM26");
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
