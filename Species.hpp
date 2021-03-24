#ifndef SPECIES_HPP
#define SPECIES_HPP

#include "Element.hpp"
#include "Skill.hpp"
#include "Engimon.hpp"
#include<iostream>
#include<vector>
using namespace std;

class Species
{
	private:
        string species;
        vector<Element> elements;
		Skill uniqueSkill;
		
	public:
		Species(string species, vector<Element> elements, const Skill& uniqueSkill);
		string getName() const;
		vector<Element> getElements() const;
		Skill getUniqueSkill() const;
};

extern vector<Species> listOfSpecies;
Species& getSpeciesByName(string species);

#endif;