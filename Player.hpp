#ifndef PLAYER_HPP
#define PLAYER_HPP

#include "Inventory.hpp"
#include "Position.hpp"
#include "Utilities.cpp"
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
}

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
        bool compareMastery(Skill s1, Skill s2);
        bool compareElmtAdv(Element el1, Element el2);
        vector<Skill> inheritSkill(Engimon A, Engimon B);
        vector<Element> inheritElmt(Engimon A, Engimon B);

        // command yang dapat dilakukan player
        void showAllEngimon();
        Engimon getActiveEngimon() const;
        void showEngimon(Engimon e);
        void switchActiveEngimon(Engimon e);
        void showSkillItems();
        void useItems(const SkillItem& si, Engimon e);
        void breed(Engimon A, Engimon B);
        void battle(Engimon enemy);
};

#endif