#include "SkillItem.hpp"
#include "Skill.hpp"
#include<iostream>
using namespace std;

SkillItem :: SkillItem(const Skill& s, string nama) : containedSkill(s)
{
	this->nama = nama;
}
