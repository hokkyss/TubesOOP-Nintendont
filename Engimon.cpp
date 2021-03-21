#include "Engimon.hpp"
#include "Skill.hpp"
#include "Skill.cpp"
#include<iostream>
using namespace std;

Engimon :: Engimon(string species, const Skill& uniqueSkill) : uniqueSkill(uniqueSkill)
{
	this->species = species;
}
