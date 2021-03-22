#include "Element.hpp"
#include "Skill.hpp"
#include "Engimon.hpp"
#include "Species.hpp"
#include<iostream>
#include<vector>
using namespace std;

Species::Species(string species, vector<Element> elements, const Skill& uniqueSkill): uniqueSkill(uniqueSkill) {
    this->species = species;
    this->elements = elements;
    this->uniqueSkill = uniqueSkill;
}

string Species::getName() const {
    return this->species;
}

vector<Element> Species::getElements() const {
    return this->elements;
}

Skill Species::getUniqueSkill() const {
    return this->uniqueSkill;
}

Species& getSpeciesByName(string species)
{
    for(int i = 0; i < listOfSpecies.size(); i++) {
        if (listOfSpecies[i].getName() == species) return listOfSpecies[i];
    }
    throw ItemNotFoundException();
}

vector<Species> listOfSpecies;