#ifndef ENGIMON_HPP
#define ENGIMON_HPP

#include "Skill.hpp"

class Engimon {
  private:
    static int countID;
    Skill skillUnique;
    vector<string> elements;
    vector<Skill> skills;
    string name;
    string species;
    int idEngimon;
    int level;
    int exp;
    int cumExp;
    int maxExp;

  public:
    Engimon(string name, string species, vector<string> elements, int maxExp, Skill skillUnique);
    ~Engimon();
    void handleAddExp(int exp);
    void Show();
};

class EngimonLiar:Engimon{
  public:
    pair <int,int> Position;
    void randomMove();
};

#endif