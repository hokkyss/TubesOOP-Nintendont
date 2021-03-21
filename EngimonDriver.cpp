#include "Element.hpp"
#include "Skill.cpp"
#include "Engimon.cpp"
#include <bits/stdc++.h>

int main(){
  Engimon * e1 = new Engimon("bambang", "charizard", {Element::Fire, Element::Water}, 5000, Tackle);
  e1->Show();
  return 0;
}