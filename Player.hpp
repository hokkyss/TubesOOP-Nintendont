#ifndef PLAYER_HPP
#define PLAYER_HPP

#include "Engimon.hpp"
#include "Inventory.hpp"
using namespace std;

class Player {
    private:
        Engimon activeEngimon;
    
    public:
        // constructor awal
        Player(Engimon starter);

        // command yang dapat dilakukan player
        void showAllEngimon();
        Engimon getActiveEngimon() const;
        void showEngimon(Engimon e);
        void switchActiveEngimon(Engimon e);

};

#endif