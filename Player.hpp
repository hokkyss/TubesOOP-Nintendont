#ifndef PLAYER_HPP
#define PLAYER_HPP

#include "Inventory.hpp"
#include "Position.hpp"
#include "Utilities.hpp"
#include "Engimon.hpp"
#include "Species.hpp"
#include <algorithm>

using namespace std;

class LevelNotEnoughException : exception
{
    const char* what() const throw()
    {
        return "Parent's level is not enough!";
    }
};

class Player {
    private:
        Position pos;
        Engimon activeEngimon;
    
    public:
        Inventory<Engimon> engimonList;
        Inventory<SkillItem> skillItemList;
        // constructor awal
        Player(const Engimon& starter);

        // utility function
        vector<Skill> inheritSkill(Engimon A, Engimon B);
        vector<Element> inheritElmt(Engimon A, Engimon B);

        // command yang dapat dilakukan player
        void showAllEngimon();
        Engimon getActiveEngimon() const;
        Position getPosition();
        void showEngimon(Engimon e);
        void switchActiveEngimon(Engimon e);
        void showSkillItems();
        void useItems(const SkillItem& si, Engimon e);
        void breed(Engimon A, Engimon B);
        void battle(Engimon enemy);
};

bool compareMastery(const Skill& s1,const Skill& s2);
    bool compareElmtAdv(const Element& el1,const Element& el2);
template <class T>
int findV(vector<T> v, T el);

#endif