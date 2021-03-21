#include "Element.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include<iostream>
#include<vector>
using namespace std;

// compilenya pake
// g++ -o nama_exe Element.cpp Skill.cpp SkillItem.cpp SkillTester.cpp
// atau
// g++ Element.cpp Skill.cpp SkillItem.cpp SkillTester.cpp -o nama_exe




int main()
{
	Skill s1 = Tackle;
	cout << s1;
	cout << endl;
	Skill s2 = s1;
	s2.setMasteryLevel(100);
	cout << s2;
	cout << endl;
	/* HINDARI BAGIAN INI */
	Skill* s3 = &Tackle;
	s3->setMasteryLevel(150);
	/* ------------------ */
	
	SkillItem i1 = TM03;
	cout << i1;
}
