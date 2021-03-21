#include "Skill.hpp"
#include "Element.hpp"
#include "Engimon.hpp"
#include <bits/stdc++.h>

int Engimon::countID = 0;

int main(){
  Engimon * e1 = new Engimon("bambang", "charizard", {Element::Fire, Element::Water}, 5000, Tackle);
  e1->showDetails();
  e1->learnSkill(BodySlam);
  e1->learnSkill(FirePledge);
  e1->learnSkill(GigaImpact);
  e1->learnSkill(IceBeam);
  e1->showDetails();
  e1->addExp(5000);
  return 0;
}