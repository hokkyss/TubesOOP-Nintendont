#include "Element.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include "gtest/gtest.h"
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

class SkillTester : public :: testing :: Test
{
	protected:
		Skill s1 = Tackle;
		Skill s2 = BullDoze;
		Skill s3 = ShockAndBurn;
};

TEST_F(SkillTester, Getter)
{
	EXPECT_EQ(s1.getBasePower(), 50);
	EXPECT_EQ(s1.getMasteryLevel(), 1);
	EXPECT_EQ(s1.getNumOfElements(), 5);
	
	EXPECT_EQ(s2.getBasePower(), 60);
	EXPECT_EQ(s2.getMasteryLevel(), 1);
	EXPECT_EQ(s2.getNumOfElements(), 1);
	
	EXPECT_EQ(s3.getBasePower(), 100);
	EXPECT_EQ(s3.getMasteryLevel(), 1);
	EXPECT_EQ(s3.getNumOfElements(), 2);
}

TEST_F(SkillTester, Setter)
{
	s1.setMasteryLevel(100);
	EXPECT_EQ(s1.getMasteryLevel(), 100);
}

TEST_F(SkillTester, Element)
{
	EXPECT_EQ(true, s1.isLearntBy(Fire));
	EXPECT_EQ(false, s2.isLearntBy(Fire));
	EXPECT_EQ(true, s2.isLearntBy(Ground));
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

