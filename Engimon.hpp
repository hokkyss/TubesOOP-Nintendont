#ifndef ENGIMON_HPP
#define ENGIMON_HPP

#include "Species.hpp"
#include "Element.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include <vector>

class Engimon:Species {
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
    Engimon(string name, string species, vector<Element> elements, const Skill& uniqueSkill, int maxExp);
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
