#include "Skill.hpp"
#include "Element.hpp"
#include<iostream>
#include<vector>
#include<exception>
using namespace std;

Skill :: Skill(string name, int basePower, Element e)
{
	this->name = name;
	this->basePower = basePower;
	this->masteryLevel = 0;
	this->numOfElements = 1;
	this->elements.push_back(e);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2)
{
	this->name = name;
	this->basePower = basePower;
	this->masteryLevel = 0;
	this->numOfElements = 2;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2, Element e3)
{
	this->name = name;
	this->basePower = basePower;
	this->masteryLevel = 0;
	this->numOfElements = 3;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2, Element e3, Element e4)
{
	this->name = name;
	this->basePower = basePower;
	this->masteryLevel = 0;
	this->numOfElements = 4;
	this->elements.push_back(e1);
	this->elements.push_back(e2);
	this->elements.push_back(e3);
	this->elements.push_back(e4);
}

Skill :: Skill(string name, int basePower, Element e1, Element e2, Element e3, Element e4, Element e5)
{
	this->name = name;
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
	this->name = s.getName();
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
	this->name = s.getName();
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
	return this->name == s.getName();
}

Skill :: ~Skill()
{
}

ostream& operator<<(ostream& out, const Skill& s)
{
	out << s.getName() << endl;
	out << "Base Power   : " << s.getBasePower() << endl;
	out << "Mastery Level: " << s.getMasteryLevel() << endl;
	out << "Can be learnt by " << s.getNumOfElements() << " element(s):" << endl;
	
	for(int i = 0; i < s.getNumOfElements(); i++)
	{
		out << i + 1 << ". " << s.getElement(i) << endl;
	}
	
	return out;
}

string Skill :: getName() const
{
	return this->name;
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
		
Element Skill :: getElement(int index) const
{
	if(index >= this->numOfElements || index < 0)
	{
		throw IndexInvalidException();
	}
	
	return this->elements[index];
}
