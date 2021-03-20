#include "Skill.hpp"
#include "Element.hpp"
#include "SkillItem.hpp"
#include<iostream>
#include<vector>
#include "Skill.cpp"
#include "SkillItem.cpp"
// #include "gtest/gtest.h"
using namespace std;

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
