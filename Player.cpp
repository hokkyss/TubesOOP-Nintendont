#include "Player.hpp"
#include <iostream>

using namespace std;

Player::Player(Engimon& starter){
    this->activeEngimon = starter;
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

void Player::move(string command){
    int x=pos.getX(), y=pos.getY();
    if (command=="w"||command=="W") x+=1;
    else if (command=="s"||command=="S") x-=1;
    else if (command=="d"||command=="D") y+=1;
    else if (command=="a"||command=="A") y-=1;

    if ((y>peta.size()||y<0)||(x>peta[y].size()||x<0)) throw command;
    else{
        pos.setX(x);
        pos.setY(y);
    }
}

bool Player::compareMastery(Skill s1, Skill s2){
    return (s1.getMasteryLevel() > s2.getMasteryLevel());
}

bool Player::compareElmtAdv(Element el1, Element el2){
    return (elmtAdv[el1][el2] > elmtAdv[el2][el1]);
}

void Player::breed(Engimon A, Engimon B){
    if(engimonList.isExist(A) && engimonList.isExist(B) && A.getLevel() >= 30 && B.getLevel() >= 30){
        string newName;
        cout << "Enter your new Engimon's name: " << endl;
        cin >> newName;
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
        // vector<Element> elmtA = A.getElements();
        // vector<Element> elmtB = B.getElements();
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
    }
    else{ throw A; }
}