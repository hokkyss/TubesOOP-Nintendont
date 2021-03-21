#include "Skill.cpp"
#include "Element.cpp"
#include "Engimon.cpp"
#include <bits/stdc++.h>

int main(){
  Engimon * e1 = new Engimon("bambang", "charizard", {Element::Fire, Element::Water}, 5000, Tackle);
  e1->showDetails();
  e1->addExp(5000);
  return 0;
}