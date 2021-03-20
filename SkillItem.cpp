#include "SkillItem.hpp"
#include "Skill.hpp"
#include<iostream>
using namespace std;

SkillItem :: SkillItem(const Skill& s, string itemName) : containedSkill(s)
{
	this->itemName = itemName;
}

ostream& operator << (ostream& out, const SkillItem& si)
{
	out << si.itemName << ": ";
	out << si.containedSkill;
	return out;
}
