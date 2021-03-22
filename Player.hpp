#ifndef PLAYER_HPP
#define PLAYER_HPP

#include "Inventory.hpp"
#include "Position.hpp"
#include "Utilities.cpp"

using namespace std;

class Player {
    private:
        Engimon activeEngimon;
        Inventory<Engimon> engimonList;
        Inventory<SkillItem> skillItemList;
        Position pos;
    
    public:
        // constructor awal
        Player(Engimon starter);

        // command yang dapat dilakukan player
        void showAllEngimon();
        Engimon getActiveEngimon() const;
        void showEngimon(Engimon e);
        void switchActiveEngimon(Engimon e);
        void showSkillItems();
        void useItems(SkillItem si, Engimon e);
        void move(string command);
};

#endif