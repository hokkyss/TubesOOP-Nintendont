#include "Skill.hpp"
#include "Element.hpp"
#include<iostream>
#include<vector>
#include<map>
#include<exception>
using namespace std;

Skill :: Skill(string name, int basePower)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 0;
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 1;
	this->elements.push_back(e);
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 2;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2, Element e3)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 3;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2, Element e3, Element e4)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 4;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
	this->elements.push_back(e4);
	listOfSkill.push_back(*this);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2, Element e3, Element e4, Element e5)
{
	this->skillName = name;
	this->basePower = basePower;
	this->masteryLevel = 1;
	this->numOfElements = 5;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
	this->elements.push_back(e4);
	this->elements.push_back(e5);
	listOfSkill.push_back(*this);
}

Skill :: Skill(const Skill& s) : skillName(s.getName())
{
	this->basePower = s.getBasePower();
	this->masteryLevel = s.getMasteryLevel();
	this->numOfElements = s.getNumOfElements();
	for(int i = 0; i < this->numOfElements; i++)
	{
		this->elements.push_back(s.getElement(i));
	}
}

void Skill :: operator=(const Skill& s)
{
	this->skillName = s.getName();
	this->basePower = s.getBasePower();
	this->masteryLevel = s.getMasteryLevel();
	this->numOfElements = s.getNumOfElements();
	for(int i = 0; i < this->numOfElements; i++)
	{
		this->elements.push_back(s.getElement(i));
	}
}

bool Skill :: operator==(const Skill& s)
{
	return this->skillName == s.getName();
}

Skill :: ~Skill()
{
}

ostream& operator<<(ostream& out, const Skill& s)
{
	out << s.getName() << endl;
	out << "Base Power   : " << s.getBasePower() << endl;
	out << "Mastery Level: " << s.getMasteryLevel() << endl;
	/*
	out << "Can be learnt by " << s.getNumOfElements() << " element(s):" << endl;
	
	for(int i = 0; i < s.getNumOfElements(); i++)
	{
		out << i + 1 << ". " << s.getElement(i) << endl;
	}
	*/
	return out;
}

string Skill :: getName() const
{
	return this->skillName;
}
		
int Skill :: getBasePower() const
{
	return this->basePower;
}

int Skill :: getMasteryLevel() const
{
	return this->masteryLevel;
}

void Skill :: setMasteryLevel(int newMasteryLevel)
{
	this->masteryLevel = newMasteryLevel;
}

int Skill :: getNumOfElements() const
{
	return this->numOfElements;
}


vector<Element> Skill :: getElements() const {
	return this->elements;
}
		
bool Skill :: isLearntBy(Element e) const
{
	for(int i = 0; i < this->numOfElements; i++)
	{
		if(this->elements[i] == e) return true;
	}
	return false;
}

Element Skill :: getElement(int index) const
{
	if(index >= this->numOfElements || index < 0)
	{
		throw IndexInvalidException();
	}
	
	return this->elements[index];
}

Skill& getSkillByName(string name)
{
	for(int i = 0; i < listOfSkill.size(); i++)
	{
		if(listOfSkill[i].getName() == name) return listOfSkill[i];
	}
	throw ItemNotFoundException();
}

