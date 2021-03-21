#ifndef ELEMENT_HPP
#define ELEMENT_HPP

#include<iostream>
using namespace std;

enum Element
{
	Fire = 0,
	Ice, 
	Ground,
	Electric,
	Water
};

ostream& operator << (ostream& out, Element e);

#endif
