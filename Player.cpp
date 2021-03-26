#include "Player.hpp"

using namespace std;

int FullInventory::currentCapacity = 0;

Player::Player(const Engimon& starter) : activeEngimonIdx(0), pos(initX,initY){
    engimonList.insert(starter);
}

void Player::showAllEngimon(){
    this->engimonList.showInventory();
}

Engimon& Player::getActiveEngimon() {
    // return this->activeEngimon;
    return this->engimonList.inventoryList[activeEngimonIdx];
}

Position& Player::getPosition(){
    return pos;
}

void Player::setPosition(Position p){
    this->pos.set(p.getX(), p.getY());
}

void Player::showEngimon(Engimon e){
    return e.showDetails();
}

void Player::switchActiveEngimon(Engimon el){
    this->activeEngimonIdx = engimonList.findItem(el);
}

void Player::showSkillItems(){
    this->skillItemList.showInventory();
}

void Player::useItems(const SkillItem& si, Engimon& e){
    if(skillItemList.isExist(si)){
        if(engimonList.isExist(e)){
            e.learnSkill(si);
            skillItemList.remove(si);
        }else throw e;
    }else throw si;
}

vector<Skill> inheritSkill(Engimon A, Engimon B){
    vector<Skill> skillA = A.getSkills();
    vector<Skill> skillB = B.getSkills();
    vector<Skill> inheritedSkills;

    // mengurutkan skillA
    sort(skillA.begin(), skillA.end(), compareMastery);
    // mengurutkan skillB
    sort(skillB.begin(), skillB.end(), compareMastery);
    
    int i = 0, j = 0;
    while(inheritedSkills.size() < 4 && inheritedSkills.size() < skillA.size()+skillB.size()){
        if(compareMastery(skillB[i], skillA[j])){
            if(findV(inheritedSkills,skillB[i]) ==  -1){
                inheritedSkills.push_back(skillB[i]);
            }
            i++;
        } else{
            if(findV(inheritedSkills, skillA[j]) ==  -1){
                inheritedSkills.push_back(skillA[j]);
            }
            j++;
        }
    }
    // mengatur mastery level engimon hasil breeding
    int ptrA, ptrB;
    for(Skill s : inheritedSkills){
        ptrA = findV(skillA, s);
        ptrB = findV(skillB, s);
        if(ptrA != -1 && ptrB != -1){
            if(skillA[ptrA].getMasteryLevel() == skillB[ptrB].getMasteryLevel()){
                s.setMasteryLevel(s.getMasteryLevel()+1);
            }
        }
    }
    return inheritedSkills;
}

vector<Element> inheritElmt(Engimon A, Engimon B){
    Element elmtA = A.getElements()[0];
    Element elmtB = B.getElements()[0];
    vector<Element> inheritedElmt;
    if(elmtA == elmtB){
        inheritedElmt.push_back(elmtA);
    } else if(elmtA != elmtB){
        if(compareElmtAdv(elmtA, elmtB)){
            inheritedElmt.push_back(elmtA);
        } else if(compareElmtAdv(elmtB, elmtA)){
            inheritedElmt.push_back(elmtB);
        } else{
            inheritedElmt.push_back(elmtA);
            inheritedElmt.push_back(elmtB);
        }
    }
    return inheritedElmt;
}

void Player::breed(Engimon& A, Engimon& B){
    if(!engimonList.isFull()){
        if(A.getLevel() > 30 && B.getLevel() > 30){
        string childName;
        cout << "Enter your new Engimon's name: " << endl;
        cin >> childName;
        
        vector<Skill> childSkill = inheritSkill(A, B);
        vector<Element> childElmt = inheritElmt(A, B);
        Species childSpecies(Emberon);
        if(isElementsSame(childElmt, A.getElements())){
            childSpecies = getSpeciesByName(A.getSpecies());
        } else if(isElementsSame(childElmt, B.getElements())){
            childSpecies = getSpeciesByName(B.getSpecies());
        } else{
            childSpecies = getSpeciesByElement(childElmt);
        }

        Engimon *child = new Engimon(childName, childSpecies);
        child->setSkill(childSkill);
        engimonList.insert(*child);

        A.setLevel(A.getLevel()-30);
        B.setLevel(B.getLevel()-30);

        cout << "Breeding successful!" << endl;
        cout << childName << " is in inventory." << endl;

        } else{ throw LevelNotEnoughException(); }
    } else{ throw InventoryFullException(); }
}

void Player::battle(EngimonLiar enemy){
    Engimon& activeEngimon = engimonList.inventoryList[this->activeEngimonIdx];
    int winner = activeEngimon.handleBattle(enemy);
    int expWon = enemy.getLevel()*EXP_MULT;
    if (winner == 2 || activeEngimon.getExp()+expWon > MAX_EXP){
        if (winner == 2) cout << activeEngimon.getName() << " has lost the battle and fainted..." << endl;
        else cout << activeEngimon.getName() << " just got max level and has to be released" << endl;
        engimonList.remove(activeEngimon);
        if(engimonList.inventoryList.size()>0) switchActiveEngimon(engimonList.inventoryList[0]);
        else throw RunOutOfEngimonException();
        cout << activeEngimon.getName() << " is the new active Engimon" << endl;
    }else {
        
        cout << "Congratulations! " << activeEngimon.getName() << " won the battle!" << endl;
        cout << activeEngimon.getName() << " gain " << expWon << " Exp" << endl;
        activeEngimon.addExp(expWon);
        
        try {
            Engimon rewardEngimon(enemy.getName(),enemy.getSpecies());
            engimonList.insert(rewardEngimon);
            SkillItem siTemp = randomSkillItem(enemy.getElements());
            cout << "\nYou got a new skill item!" << endl;
            cout << siTemp << endl;
            skillItemList.insert(siTemp);
        }catch (InventoryFullException errFull){
            throw errFull;
        }catch (ItemAlreadyExistedException errExisted){
            throw errExisted;
        }
    }
}

bool compareMastery(const Skill& s1,const Skill& s2){
    return (s1.getMasteryLevel() > s2.getMasteryLevel());
}

bool compareElmtAdv(const Element& el1,const Element& el2){
    return (elmtAdv[el1][el2] > elmtAdv[el2][el1]);
}

template <class T>
int findV(vector<T> v, T el){
    for(int i=0;i<v.size();i++){
        if(v[i]==el) return i;
    }
    return -1;
}

/* int main (){
    Engimon *e = new Engimon("Emberon","Emberon");
    Player p(*e);
    p.showEngimon(*e);
    e = new Engimon("Hailon","Hailon");
    p.battle(*e);
    p.showAllEngimon();
} */
