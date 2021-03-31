#include "Engimon.hpp"

using namespace std;

int Engimon::countID = 0;

double elmtAdv[5][5] = {{1, 2, 0.5, 1, 0}, {0, 1, 2, 0.5, 1}, {1.5, 0, 1, 2, 1}, {1, 1.5, 0, 1, 2}, {2, 1, 1, 0, 1}};

Engimon::Engimon(string name, string species, vector<Element> elements, const Skill& uniqueSkill, vector<string> response) : Engimon(name, Species(species, elements, uniqueSkill, response)) {};

Engimon::Engimon(string name, Species species, int level): Engimon(name, Species(species)) {
  this->level = level;
  this->exp = 0;
  this->cumExp = (level-1)*EXP_PER_LEVEL;
  this->maxExp = MAX_EXP;
}

Engimon::Engimon(string name, Species species, vector<Engimon> parents) : Engimon(name, Species(species)) {
  if (parents.size() != 2) throw ParentsInvalidException();

  for(int i = 0; i<parents.size(); i++){
    this->parentName.push_back(parents[i].getName());
    this->parentSpecies.push_back((parents[i].getSpecies()));
  }
  
}

Engimon::Engimon(string name, Species species): Species(species) {
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

Engimon::Engimon(string name, string species, int level): Engimon(name, getSpeciesByName(species), level) {};

Engimon::~Engimon(){}

string Engimon::getName(){
  return this->name;
}

int Engimon::getExp(){
  return this->exp;
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

string Engimon::getSpecies() {
  return this->species;
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
  cout << "Parents :\n";
  if (this->parentName.size() != 2 && this->parentSpecies.size() != 2) cout << "-";
  else {
    for(int i=0; i<this->parentName.size(); i++){
      cout << "> " << parentName[i] << " - " << parentSpecies[i] << endl;
    } 
  }
  cout << endl;
  cout <<  "=======ENGIMON'S DETAIL=======" << endl;
}

void Engimon::showSkills() const {
  cout <<  "=======SKILLS DETAIL=======" << endl;
  printVector<Skill>(this->skills, "", true, true);
  cout <<  "=======SKILLS DETAIL=======" << endl;
}

bool Engimon::isSkillCompatible(const Skill& skill) const {
  for(int i = 0; i < this->elements.size(); i++) {
    if(skill.isLearntBy(this->elements[i])) return true;
  }
  return false;
}

bool Engimon::hasLearnt(const Skill& skill) const {
  for(int i = 0; i < this->skills.size(); i++) {
    if(this->skills[i].getName() == skill.getName()) return true;
  }
  return false;
}

void Engimon::learnSkill(const SkillItem& skillItem) {
  // TODO: kalau beda element, bakal throw exception
  Skill skill = skillItem.getContainedSkill();
  if (!isSkillCompatible(skill)) {
    throw SkillNotCompatibleException();
  }

  if (this->hasLearnt(skill)) {
    throw SkillExistException();
  }

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

void Engimon::addSkill(const Skill& skill){
  if (this->skills.size() < 4) this->skills.push_back(skill);
}

void Engimon::setSkill(const vector<Skill> target){
  if(target.size()<4){
    (this->skills).clear();
    for(int i = 0; i<target.size(); i++){
      this->skills.push_back(target[i]);
    }
  }
}

void Engimon::setLevel(int level){
  this->level = level;
  this->cumExp = (level-1) * 100;
}

double Engimon::countElmtAdvPower(Engimon def) {
    double res = -1;
    
    for (Element atkElmt : this->getElements()) {
        for (Element defElmt : def.getElements()) {
            if (elmtAdv[atkElmt][defElmt] > res)
                res = elmtAdv[atkElmt][defElmt];
        }
    }

    return res;
}

double Engimon::countSkillPower() {
    double res = 0;

    for (Skill s : skills) {
        res += s.getBasePower() * s.getMasteryLevel();
    }

    return res;
}

int Engimon::handleBattle(Engimon enemy) {
    double powerMyEngimon = this->getLevel() * this->countElmtAdvPower(enemy) + this->countSkillPower();
    double powerEnemy = enemy.getLevel() * enemy.countElmtAdvPower(*this) + enemy.countSkillPower();

    cout << "Power of the MyEngimon: " << powerMyEngimon << endl;
    cout << "VS" << endl;
    cout << "Power of the Enemy: " << powerEnemy << endl;

    return (powerMyEngimon >= powerEnemy) ? 1 : 2;
}

EngimonLiar::EngimonLiar(const Species& sp, Position pos, int level) : Engimon(sp.getName(), sp, level), position(pos){}

Position EngimonLiar::getPosition() const { return position; }

void EngimonLiar::setPosition(Position p) {
    this->position.setX(p.getX());
    this->position.setY(p.getY());
}

void EngimonLiar::printSummary() {
  cout << "\n========ENEMY DETAIL=======" << endl;
  cout << "Spesies : " << this->species << endl;
  cout << "Elements : ";
  printVector<Element>(this->elements, ", ", false, false);
  cout << endl;
  cout << "Level : " << this->getLevel() << endl;
  cout <<  "=======ENEMY DETAIL=======\n" << endl;
}

bool Engimon::operator == (Engimon e) {
  return (this->name == e.name && this->species == e.species && this->level == e.level);
}

void Engimon::interact() {
  cout<<this->name<<" : ";
  Species::interact();
  cout<<"\n";
}