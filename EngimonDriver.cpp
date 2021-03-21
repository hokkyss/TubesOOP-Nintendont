#include "Element.hpp"
#include "Skill.cpp"
#include "Engimon.cpp"

int main(){
  Skill s1 = new Skill("skill1",50, "Fire");
  Engimon e1 = new Engimon("bambang", "charizard", ["api","air"], 5000, s1);
  return 0;
}