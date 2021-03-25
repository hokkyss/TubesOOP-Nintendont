#include "SkillItem.hpp"

using namespace std;

SkillItem :: SkillItem(const Skill& s, string itemName) : containedSkill(s)
{
	this->itemName = itemName;
	listOfSkillItem.push_back(*this);
}

SkillItem :: SkillItem(const SkillItem& si) : containedSkill(si.getContainedSkill())
{
	this->itemName = si.getName();
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


string SkillItem :: getName() const
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
		if(listOfSkillItem[i].getName() == name) return listOfSkillItem[i];
	}
	throw ItemNotFoundException();
}

SkillItem randomSkillItem(vector<Element> elements)
{
	cout<<"randSkillItem 1\n";
	vector<SkillItem> candidates;
	int listOfSkillItemSize = listOfSkillItem.size();
	cout<<"randSkillItem 2\n";
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
			SkillItem * temp = new SkillItem(listOfSkillItem[i]);
			candidates.push_back(*temp);
		}
	}
	cout<<"randSkillItem 3\n";
	int randomIndex = rand() % candidates.size();
	cout<<"randSkillItem 4\n";
	cout<<"INI SIZE "<<candidates.size()<<endl;
	cout<<"INI RANDOM IDX "<<randomIndex<<endl;
	return candidates[randomIndex];
}

vector<SkillItem> listOfSkillItem;

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
