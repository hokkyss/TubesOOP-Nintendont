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
  return 0;
}