#ifndef ENGIMON_HPP
#define ENGIMON_HPP

#define MAX_EXP 4900
#define EXP_PER_LEVEL 100

#include "Element.hpp"
#include "Species.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include "Utilities.hpp"
#include "Position.hpp"

#include <string>
#include <vector>

class SkillNotCompatibleException : exception
{
	const char* what() const throw()
	{
		return "Skill not compatible!";
	}
};

class Engimon : public Species {
  private:
    static int countID;
    int idEngimon;
    string name;
    // parent bisa banyak
    vector<string> parentName;
    vector<string> parentSpecies;

    int level;
    int exp;
    int cumExp;
    int maxExp;
    vector<Skill> skills;

  public:
    Engimon(string name, string species, vector<Element> elements, const Skill& uniqueSkill);
    Engimon(string name, const Species& species, int level);
    Engimon(string name, const Species& species);
    Engimon(string name, string species);
    ~Engimon();

    //getter
    int getLevel();
    int getExp();
    string getName();
    vector<string> getParentName();
    vector<string> getParentSpecies();
    vector<Element> getElements();
    vector<Skill> getSkills();
    string getSpecies();

    //setter
    void setSkill(const vector<Skill> skills);
    void setLevel(int level);

    void addExp(int exp);
    void showDetails() const;
    void showSkills() const;
    bool isSkillCompatible(const Skill& skill) const;
    void learnSkill(const SkillItem& skillItem);
    void addSkill(const Skill& skill);
    void breed(Engimon couple);
    double countElmtAdvPower(Engimon def);
    double countSkillPower();
    int handleBattle(Engimon enemy);
    bool operator== (Engimon e);
};

class EngimonLiar : public Engimon{
  private:
    Position position;
  public:
      EngimonLiar();
      EngimonLiar(const Species &sp, Position pos, int level);
      Position getPosition() const;
      void setPosition(Position p);
};


#endif
