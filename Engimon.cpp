#include "Engimon.hpp"

Engimon::Engimon(string name, string species, vector<Element> elements, int maxExp, Skill skillUnique){
  this->name = name;
  this->species = species;
  for(int i = 0; i < elements.size(); i++){
    this->elements[i] = elements[i];
  }
  this->level = 1;
  this->exp = 0;
  this->cumExp = 0;
  this->maxExp = maxExp;
  this->skillUnique = skillUnique;
  Engimon::countID++;
}

Engimon::~Engimon(){
  cout << this->name << " is dead :(" << endl;
}

void Engimon::addExp(int exp){
  this->cumExp += exp;
  this->exp = this->cumExp % 100;
  this->level = (this->exp/100) + 1;

  if(this->cumExp>=this->maxExp){
    this->~Engimon();
  }
}

void Engimon::showDetails(){
  cout << "=======ENGIMON'S DETAIL=======" << endl;
  cout << "nama : " << this->name << endl;
  cout << "spesies : " << this->species << endl;
  cout << "elements : " << endl;
  for(int i = 0; i < this->elements.size(); i++){
    cout<<this->elements[i];
    if(i!=this->elements.size()-1){
      cout<<",";
    }
  }
  cout<<endl;
  cout << "level : " << this->level << endl;
  cout << "exp : " << this->exp << endl;
  cout <<  "=======ENGIMON'S DETAIL=======" << endl;
}