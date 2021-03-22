#include "Element.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
// #include "gtest/gtest.h"
#include<iostream>
#include<vector>
// #include "Element.cpp"
// #include "Skill.cpp"
// #include "SkillItem.cpp"
using namespace std;

// compilenya pake
// g++ -o nama_exe Element.cpp Skill.cpp SkillItem.cpp SkillTester.cpp
// atau
// g++ Element.cpp Skill.cpp SkillItem.cpp SkillTester.cpp -o nama_exe

/*
class SkillTester : public :: testing :: Test
{
	protected:
		Skill s1 = Tackle;
		Skill s2 = Bulldoze;
		Skill s3 = ShockAndBurn;
}
TEST_F(SkillTester, Getter)
{
	EXPECT_EQ(s1.getBasePower(), 50);
	EXPECT_EQ(s1.getMasteryLevel(), 0);
	EXPECT_EQ(s1.getNumOfElements(), 5);
	
	EXPECT_EQ(s2.getBasePower(), 60);
	EXPECT_EQ(s2.getMasteryLevel(), 0);
	EXPECT_EQ(s2.getNumOfElements(), 1);
	
	EXPECT_EQ(s3.getBasePower(), 100);
	EXPECT_EQ(s3.getMasteryLevel(), 0);
	EXPECT_EQ(s3.getNumOfElements(), 2);
}
TEST_F(SkillTester, Setter)
{
	s1.setMasteryLevel(100);
	EXPECT_EQ(s1.getMasteryLevel(), 100);
}
TEST_F(SkillTester, Exception)
{
	try
	{
		s1.getElement(6);
		FAIL() << "Exception index invalid";
	}
	catch (exception& e)
	{
		EXPECT_EQ(e.what(), "Index invalid");
	}
}
int main(int argc, char** argv)
{
	::testing::InitGoogleTest(&argc, argv);
	return RUN_ALL_TESTS();
}
*/
void test(const SkillItem& skillItem)
{
	Skill s = skillItem.getContainedSkill();
	s.setMasteryLevel(5);
	cout << s << endl;
}

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
	cout << Tackle << endl;
	/* ------------------ */
	
	Skill s4 = getSkillByName("Electrolysis");
	cout << s4 << endl;
	
	SkillItem i1 = TM03;
	Skill s5 = TM03.getContainedSkill();
	cout << s5 << endl;
	
	SkillItem i2 = getSkillItemByName("TM10");
	cout << i2 << endl;
	
	test(TM05);
	cout << TM05 << endl;
}
