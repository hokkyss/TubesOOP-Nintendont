#include "Player.hpp"
#include <iostream>

using namespace std;

Player::Player(const Engimon& starter) : activeEngimon(starter), pos(pos){
    engimonList.insert(starter);
    pos.set(0,0);
}

void Player::showAllEngimon(){
    this->engimonList.showInventory();
}

Engimon Player::getActiveEngimon() const{
    return this->activeEngimon;
}

void Player::showEngimon(Engimon e){
    return e.showDetails();
}

void Player::switchActiveEngimon(Engimon e){
    this->activeEngimon = e;
}

void Player::showSkillItems(){
    this->skillItemList.showInventory();
}

void Player::useItems(const SkillItem& si, Engimon e){
    if(skillItemList.isExist(si)){
        if(engimonList.isExist(e)){
            //e.learn(si);
            skillItemList.remove(si);
        }else throw e;
    }else throw si;
}

bool Player::compareMastery(Skill s1, Skill s2){
    return (s1.getMasteryLevel() > s2.getMasteryLevel());
}

bool Player::compareElmtAdv(Element el1, Element el2){
    return (elmtAdv[el1][el2] > elmtAdv[el2][el1]);
}

vector<Skill> Player::inheritSkill(Engimon A, Engimon B){
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
            if(std::find(inheritedSkills.begin(), inheritedSkills.end(), skillB[i]) ==  inheritedSkills.end()){
                inheritedSkills.push_back(skillB[i]);
            }
            i++;
        } else{
            if(std::find(inheritedSkills.begin(), inheritedSkills.end(), skillB[i]) ==  inheritedSkills.end()){
                inheritedSkills.push_back(skillA[j]);
            }
            j++;
        }
    }
    // mengatur mastery level engimon hasil breeding
    std::vector<Skill>::iterator ptrA, ptrB;
    for(Skill s : inheritedSkills){
        ptrA = std::find(skillA.begin(), skillA.end(), s);
        ptrB = std::find(skillB.begin(), skillB.end(), s);
        if(ptrA != skillA.end() && ptrB != skillB.end()){
            if(ptrA->getMasteryLevel() == ptrB->getMasteryLevel()){
                s.setMasteryLevel(s.getMasteryLevel()+1);
            }
        }
    }
    return inheritedSkills;
}

vector<Element> Player::inheritElmt(Engimon A, Engimon B){
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

void Player::breed(Engimon A, Engimon B){
    if(engimonList.isFull()){
        if(A.getLevel() >= 30 && B.getLevel() >= 30){
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

        cout << "Breeding successful!" << endl;
        cout << childName << " is in inventory." << endl;

        } else{ throw LevelNotEnoughException(); }
    } else{ throw InventoryFullException(); }
}

void Player::battle(Engimon enemy){
    int winner = handleBattle(activeEngimon,enemy);
    int expWon = enemy.getLevel()*15;

    if (winner == 2 || activeEngimon.getExp()+expWon > MAX_EXP){
        engimonList.remove(activeEngimon);
        if(engimonList.inventoryList.size()>0) switchActiveEngimon(engimonList.inventoryList[0]);
        else throw enemy;
    }else {
        activeEngimon.addExp(expWon);
        try {
            engimonList.insert(enemy);
            skillItemList.insert(randomSkillItem(enemy.getElements()));
        }catch (InventoryFullException err){
            throw err;
        }
    }
}
