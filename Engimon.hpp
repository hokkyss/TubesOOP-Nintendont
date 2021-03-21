#ifndef ENGIMON_HPP
#define ENGIMON_HPP

#include "Element.hpp"
#include "Skill.hpp"
#include<vector>

class Engimon {
  private:
    // static int countID;
    Skill uniqueSkill;
    vector<Element> elements;
    // vector<Skill> skills;
    string name;
    string species;
    int idEngimon;
    int level;
    int exp;
    int cumExp;
    int maxExp;

  public:
    Engimon(string name, string species, vector<Element> elements, int maxExp, const Skill& uniqueSkill);
    ~Engimon();
    void addExp(int exp);
    void showDetails();
};

class EngimonLiar:Engimon{
  public:
    pair <int,int> Position;
    void randomMove();
};

#endif