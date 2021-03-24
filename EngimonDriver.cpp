#include "Skill.hpp"
#include "SkillItem.hpp"
#include "Element.hpp"
#include "Engimon.hpp"
#include <bits/stdc++.h>

int Engimon::countID = 0;

int main(){
  Engimon * e1 = new Engimon("bambang", "Frederon");
  e1->showDetails();
  e1->learnSkill(TM20);
  e1->learnSkill(TM02);
  e1->learnSkill(TM09);
  e1->learnSkill(TM05);
  e1->showDetails();
  e1->addExp(5000);
  cout<<getSpeciesByElement({Element::Ice, Element::Fire, Element::Ground}).getName();
  Engimon * e2 = new Engimon("kentang", "Gustmon");
  e2->showDetails();
  e2->addSkill(HydroPump);
  e2->showDetails();
  vector<Skill> skills;
  skills.push_back(HydroCannon);
  skills.push_back(HydroPump);
  skills.push_back(EarthPower);
  e2->setSkill(skills);
  e2->showDetails();
  e2->addExp(5000);
  return 0;
}