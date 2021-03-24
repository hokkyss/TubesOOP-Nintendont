#ifndef ENGIMON_HPP
#define ENGIMON_HPP

#include "Species.hpp"
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
    Engimon(string name, const Species& species, int maxExp);
    Engimon(string name, string species, int maxExp);
    ~Engimon();
    int getLevel();
    vector<string> getParentName();
    vector<string> getParentSpecies();
    vector<Element> getElements();
    vector<Skill> getSkills();
    void addExp(int exp);
    void showDetails() const;
    void showSkills() const;
    void learnSkill(const SkillItem& skillItem);
    void breed(Engimon couple);
};

class EngimonLiar:Engimon{
  public:
  	// pake Position???
    pair <int,int> Position;
    void randomMove();
};

#endif
