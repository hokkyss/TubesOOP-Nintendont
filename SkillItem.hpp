#ifndef SKILL_ITEM_HPP
#define SKILL_ITEM_HPP

#include "Skill.hpp"
#include<iostream>
using namespace std;

class SkillItem
{
	public:
		string nama;
		Skill containedSkill;
		SkillItem(const Skill& s, string nama);
};

// harus extern?
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
SkillItem TM24(ElectroLysis, "TM24");
SkillItem TM25(ColdRefrigerator, "TM25");
SkillItem TM26(ContradictingShock, "TM26");

/*
SkillItem TM01(GigaImpact);
SkillItem TM02(BlastBurn);
SkillItem TM03(SubzeroSlammer);
SkillItem TM04(TectonicRage);
SkillItem TM05(GigavoltHavoc);
SkillItem TM06(HydroCannon);
SkillItem TM07(IceHammer);
SkillItem TM08(MaxHailstorm);
SkillItem TM09(FreezeJolt);
SkillItem TM10(FreezingFlame);
SkillItem TM11(Thunder);
SkillItem TM12(Mudflood);
SkillItem TM13(SteamBlast);
SkillItem TM14(ThunderBlast);
SkillItem TM15(ShockAndBurn);
SkillItem TM16(Eruption);
SkillItem TM17(WeatherBall);
SkillItem TM18(TriAttack);
SkillItem TM19(EarthPower);
SkillItem TM20(Korslet);
SkillItem TM21(MeltTheGround);
SkillItem TM22(ShockTheFlame);
SkillItem TM23(FreezeDry);
SkillItem TM24(ElectroLysis);
SkillItem TM25(ColdRefrigerator);
SkillItem TM26(ContradictingShock);
*/

#endif
