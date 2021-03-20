#include "Skill.hpp"
#include "Element.hpp"
#include<iostream>
#include<vector>
#include "gtest/gtest.h"
using namespace std;

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
