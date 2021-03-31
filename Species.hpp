#ifndef SPECIES_HPP
#define SPECIES_HPP

#include "Element.hpp"
#include "Skill.hpp"
#include <algorithm>

using namespace std;

class Species
{
	public:
		string species;
		vector<Element> elements;
		vector<string> response;
		Skill uniqueSkill;

		Species(string species, vector<Element> elements, const Skill& uniqueSkill, vector<string> response);
		Species(const Species& s);
		
		string getName() const;
		vector<Element> getElements() const;
		Skill getUniqueSkill() const;
		void operator=(const Species& s);
		virtual void interact();
};

extern vector<Species> listOfSpecies;
Species& getSpeciesByName(string species);
Species& getSpeciesByElement(vector<Element> elements);
bool isElementsSame(vector<Element> e1, vector<Element> e2);

//single element
extern Species Emberon;
extern Species Hailon;
extern Species Soliust;
extern Species Bulbmon;
extern Species Aquaron;

extern Species Sparkymon;
extern Species Icypicy;
extern Species CacingAlaska;
extern Species Chupika;
extern Species Flooduf;

//double element
extern Species Coldhell;
extern Species Magmatuar;
extern Species Electrosion;
extern Species Sealame;

extern Species Antartic;
extern Species Culcas;
extern Species Labile;

extern Species Gustmon;
extern Species Trenchmon;

extern Species Voltense;

//triple element
extern Species Ficigmon;
extern Species Circogord;
extern Species Icify;
extern Species LovaMagama;
extern Species Tetonicy;
extern Species PerfectDisaster;

extern Species Iglosify;
extern Species Trofii;
extern Species Syverter;

extern Species Oonga;

//4 elements
extern Species Hokkien;
extern Species Arleen;
extern Species Maxeew;
extern Species Shijeew;
extern Species Pegow;

extern Species Frederon;

#endif