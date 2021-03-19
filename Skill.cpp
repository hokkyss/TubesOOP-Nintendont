#include "Skill.hpp"
#include<iostream>
#include<vector>
#include<exception>
using namespace std;

Skill :: Skill(int basePower, Element e)
{
	this->basePower = basePower;
	this->masteryLevel = 0;
	this->numOfElements = 1;
	this->elements.push_back(e);
}

Skill :: Skill(int basePower, Element e1, Element e2)
{
	this->basePower = basePower;
	this->masteryLevel = 0;
	this->numOfElements = 2;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
}

Skill :: Skill(int basePower, Element e1, Element e2, Element e3)
{
	this->basePower = basePower;
	this->masteryLevel = 0;
	this->numOfElements = 3;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
}

Skill :: Skill(int basePower, Element e1, Element e2, Element e3, Element e4)
{
	this->basePower = basePower;
	this->masteryLevel = 0;
	this->numOfElements = 4;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
	this->elements.push_back(e4);
}

Skill :: Skill(int basePower, Element e1, Element e2, Element e3, Element e4, Element e5)
{
	this->basePower = basePower;
	this->masteryLevel = 0;
	this->numOfElements = 5;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
	this->elements.push_back(e4);
	this->elements.push_back(e5);
}

Skill :: Skill(const Skill& s)
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
	this->basePower = s.getBasePower();
	this->masteryLevel = s.getMasteryLevel();
	this->numOfElements = s.getNumOfElements();
	for(int i = 0; i < this->numOfElements; i++)
	{
		this->elements.push_back(s.getElement(i));
	}
}

int Skill :: getMasteryLevel() const
{
	return this->masteryLevel;
}


void Skill :: setMasteryLevel(int newMasteryLevel)
{
	this->masteryLevel = newMasteryLevel;
}
		
int Skill :: getBasePower() const
{
	return this->basePower;
}

int Skill :: getNumOfElements() const
{
	return this->numOfElements;
}
		
Element Skill :: getElement(int index) const
{
	if(index >= this->numOfElements || index < 0)
	{
		throw IndexInvalidException();
	}
	
	return this->elements[index];
}
