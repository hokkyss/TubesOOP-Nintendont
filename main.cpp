/* Libraries */
#include <iostream>

/* Classes */
#include "Element.hpp"
#include "Engimon.hpp"
#include "Player.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include "Inventory.hpp"
#include "Species.hpp"

/* Utilities */
#include "Utilities.hpp"

using namespace std;

#define RESPAWN_TURN 10

int main() {
    /* Const Declaration */
    const string filePath = "./input/contoh.txt";

    /* Var Declaration */
    string command;
    int turn = 0;

    /* Initialization Phase */
    initElmtAdv();
    initPeta(filePath);

    /* In Game Phase */
    while(cin >> command){
        if (turn % RESPAWN_TURN == 0)
            spawnWildEngimons();
        printPeta();
        turn++;
    }

    return 0;
}