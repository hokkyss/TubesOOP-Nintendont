#ifndef PLAYER_HPP
#define PLAYER_HPP

#include "Position.hpp"
#include "Utilities.hpp"
#include "Engimon.hpp"
#include "SkillItem.hpp"
#include "Inventory.hpp"
#include "Inventory.cpp"
#include "Exception.hpp"
#include <iostream>

#define initX 0
#define initY 0
#define EXP_MULT 15

using namespace std;

class Player {
    private:
        Position pos;
        int activeEngimonIdx;
    
    public:
        Inventory<Engimon> engimonList;
        Inventory<SkillItem> skillItemList;
        // constructor awal
        Player(const Engimon& starter);

        // command yang dapat dilakukan player
        void showAllEngimon();
        Engimon& getActiveEngimon();
        Position& getPosition();
        void setPosition(Position p);
        void showEngimon(Engimon e);
        void switchActiveEngimon(Engimon e);
        void showSkillItems();
        void useItems(const SkillItem& si, Engimon& e);
        void breed(Engimon& A, Engimon& B);
        void battle(EngimonLiar enemy);
};


// utility function
vector<Skill> inheritSkill(Engimon A, Engimon B);
vector<Element> inheritElmt(Engimon A, Engimon B);

bool compareMastery(const Skill& s1,const Skill& s2);
bool compareElmtAdv(const Element& el1,const Element& el2);

template <class T>
int findV(vector<T> v, T el);
void initTemplate();

#endif