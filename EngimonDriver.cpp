/* Libraries */
#include <iostream>
#include <algorithm>

/* Classes */
#include "Element.hpp"
#include "Engimon.hpp"
#include "Player.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include "Inventory.hpp"
#include "Species.hpp"
#include "Direction.hpp"

/* Utilities */
#include "Utilities.hpp"

int main(){
  Engimon e1("bambang", "Frederon");
  //e1->showDetails();
  e1.learnSkill(TM20);
  e1.learnSkill(TM02);
  e1.learnSkill(TM09);
  // e1.learnSkill(TM05);
  // e1.showDetails();
  e1.addExp(5000);
  //cout<<getSpeciesByElement({Element::Ice, Element::Fire, Element::Ground}).getName();

  /* Engimon * e2 = new Engimon("kentang", "Gustmon");
  //e2->showDetails();
  e2->addSkill(HydroPump);
  //e2->showDetails();
  vector<Skill> skills;
  skills.push_back(HydroCannon);
  skills.push_back(HydroPump);
  skills.push_back(EarthPower);
  e2->setSkill(skills);
  //e2->showDetails();
  e2->addExp(5000);
  */

  return 0;
  //p.engimonList.insert(*e2);

  //p->breed(*e1, *e2);

}