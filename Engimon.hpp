#ifndef ENGIMON_HPP
#define ENGIMON_HPP

#include "Skill.hpp"
#include<vector>

class Engimon {
  private:
    static int countID;
    Skill skillUnique;
    vector<Element> elements;
    vector<Skill> skills;
    string name;
    string species;
    int idEngimon;
    int level;
    int exp;
    int cumExp;
    int maxExp;

  public:
    Engimon(string name, string species, vector<Element> elements, int maxExp, Skill skillUnique);
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