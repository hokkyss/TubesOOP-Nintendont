#ifndef ENGIMON_HPP
#define ENGIMON_HPP

#include "Element.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include <vector>

class Engimon {
  private:
    static int countID;
    int idEngimon;
    string name;
    string species;
    // parent bisa banyak
    vector<string> parentName;
    vector<string> parentSpecies;

    int level;
    int exp;
    int cumExp;
    int maxExp;
    vector<Element> elements;
    Skill uniqueSkill;
    vector<Skill> skills;

  public:
    Engimon(string name, string species, vector<Element> elements, int maxExp, const Skill& uniqueSkill);
    ~Engimon();
    void addExp(int exp);
    void showDetails() const;
    void showSkills() const;
    void learnSkill(const SkillItem& skillItem);
};

class EngimonLiar:Engimon{
  public:
  	// pake Position???
    pair <int,int> Position;
    void randomMove();
};

#endif
