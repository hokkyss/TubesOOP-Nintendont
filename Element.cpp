#include "Element.hpp"
#include<iostream>
using namespace std;

ostream& operator << (ostream& out, Element e)
{
	if(e == Fire) out << "Fire";
	else if(e == Ice) out << "Ice";
	else if(e == Ground) out << "Ground";
	else if(e == Electric) out << "Electric";
	else if(e == Water) out << "Water";
	
	return out;
}
