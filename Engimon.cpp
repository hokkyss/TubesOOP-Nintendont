#include "Utilities.cpp"
#include "Engimon.hpp"
#include "Element.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include <string.h>
#include <bits/stdc++.h>
using namespace std;

Engimon::Engimon(string name, const Species& species): Species(species) {
  this->idEngimon = Engimon::countID;
  this->name = name;
  this->level = 1;
  this->exp = 0;
  this->cumExp = 0;
  this->maxExp = MAX_EXP;
  this->skills.push_back(species.uniqueSkill);
  Engimon::countID++;
}

Engimon::Engimon(string name, string species): Engimon(name, getSpeciesByName(species)) {};

Engimon::Engimon(string name, string species, vector<Element> elements, const Skill& uniqueSkill) : Engimon(name, Species(species, elements, uniqueSkill)) {};

Engimon::~Engimon(){
  cout << this->name << " is dead :(" << endl;
}

string Engimon::getName(){
  return this->name;
}

int Engimon::getLevel() {
  return this->level;
}

vector<string> Engimon::getParentName() {
  return this->parentName;
}

vector<string> Engimon::getParentSpecies() {
  return this->parentSpecies;
}

vector<Element> Engimon::getElements() {
  return this->elements;
}

vector<Skill> Engimon::getSkills() {
  return this->skills;
}

void Engimon::addExp(int exp){
  this->cumExp += exp;
  this->exp = this->cumExp % 100;

  if (this->level != (this->cumExp/100) + 1) {
    this->level = (this->cumExp/100) + 1;
    cout << this->name << " has leveled up to level " << this->level << endl;
  }

  if(this->cumExp>=this->maxExp){
    this->~Engimon();
  }
}

void Engimon::showDetails() const {
  cout << "=======ENGIMON'S DETAIL=======" << endl;
  cout << "ID Engimon : " << this->idEngimon << endl;
  cout << "Nama : " << this->name << endl;
  cout << "Spesies : " << this->species << endl;
  cout << "Elements : ";
  printVector<Element>(this->elements, ", ", false, false);
  cout << endl;
  cout << "Level : " << this->level << endl;
  cout << "Exp : (" << this->exp << "/100)" << endl;
  cout << "Total Exp : " << this->cumExp << endl;
  cout << "Unique Skill : " << this->uniqueSkill.getName() << endl;
  cout << "Skills : ";
  // gabisa pake printvector karena hanya nama saja
  for(int i = 0; i < this->skills.size(); i++){
    cout << this->skills[i].getName();
    if(i != this->skills.size() - 1){
      cout << ", ";
    }
  }
  cout << endl;
  cout << "Parents : ";
  if (this->parentName.size() == 0 && this->parentSpecies.size() == 0) cout << "-";
  cout << endl;
  printVector<string>(this->parentName, ", ", false, false);
  printVector<string>(this->parentSpecies, ", ", false, false);
  cout <<  "=======ENGIMON'S DETAIL=======" << endl;
}

void Engimon::showSkills() const {
  cout <<  "=======SKILLS DETAIL=======" << endl;
  printVector<Skill>(this->skills, "", true, true);
  cout <<  "=======SKILLS DETAIL=======" << endl;
}

void Engimon::learnSkill(const SkillItem& skillItem) {
  // TODO: kalau beda element, bakal throw exception
  Skill skill = skillItem.getContainedSkill();
  if (this->skills.size() < 4) {
    this->skills.push_back(skill);
    cout << this->name << " learned " << skill.getName() << "!" << endl;
  } else {
    string answer;
    int idToDelete;
    this->showSkills();
    cout << this->name << " wants to learn the skill " << skill.getName() << "." << endl;
    cout << "However, " << this->name << " already knows four skills." << endl;
    cout << "Should a move be deleted and replaced with " << skill.getName() << "? (Y/N)" << endl;
    cin >> answer;
    if (answer == "Y") {
      cout << "Enter the skill id you want to replace (-1 to cancel): " << endl;
      cin >> idToDelete;
      if (idToDelete < 0) {
        cout << "Cancelled" << endl;
        return;
      } else if (idToDelete > 4) {
        cout << "Not a valid ID" << endl;
        return;
      } else {
        cout << "1, 2, and ... ... ... Poof!" << endl;
        cout << this->name << "forgot how to use " << this->skills.at(idToDelete - 1).getName() << endl;
        this->skills.at(idToDelete - 1) = skill;
        cout << this->name << " learned " << skill.getName() << "!" << endl;
      }
    }
  }
}
