#include "Engimon.hpp"

Engimon::Engimon(string name, string species, vector<Element> elements, int maxExp, Skill skillUnique){
  this->name = name;
  this->species = species;
  for(int i = 0; i<elements.size(); i++){
    this->elements[i] = elements[i];
  }
  this->maxExp = maxExp;
  this->skillUnique = skillUnique;
}

Engimon::~Engimon(){}

void Engimon::handleAddExp(int exp){
  this->exp += exp;
  this->level = this->exp/100;
  if(this->exp>=this->maxExp){
    this->~Engimon();
  }
}

void Engimon::Show(){
  cout  <<  "=======ENGIMON'S DETAIL======="<<endl;
  cout  <<  "nama : "<<this->name<<endl;
  cout  <<  "spesies : "<<this->species<<endl;
  cout  <<  "elements : ";
  for(int i = 0; i<this->elements.size(); i++){
    cout<<this->elements[i];
    if(i!=this->elements.size()-1){
      cout<<",";
    }
  }
  cout<<endl;
  cout << "exp : "<<this->exp<<endl;
  cout << "level : "<< this->level<<endl;
  cout  <<  "=======ENGIMON'S DETAIL======="<<endl;
}