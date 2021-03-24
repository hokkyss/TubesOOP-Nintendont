#ifndef ENGIMON_HPP
#define ENGIMON_HPP

#define MAX_EXP 4900
#define EXP_PER_LEVEL 100

#include "Species.hpp"
#include "Element.hpp"
#include "Skill.hpp"
#include "Position.hpp"
#include "SkillItem.hpp"
#include <vector>

class Engimon:public Species {
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
    int getLevel();
    string getName();
    vector<string> getParentName();
    vector<string> getParentSpecies();
    vector<Element> getElements();
    vector<Skill> getSkills();
    void addExp(int exp);
    void showDetails() const;
    void showSkills() const;
    void learnSkill(const SkillItem& skillItem);
    void addSkill(const Skill& skill);
    void setSkill(const vector<Skill> skills);
    void breed(Engimon couple);
};

#endif
