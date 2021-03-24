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
#include "Utilities.cpp"

using namespace std;

#define RESPAWN_TURN 10

double countElmtAdvPower(Engimon atk, Engimon def) {
    double res = -1;
    
    for (Element atkElmt : atk.getElements()) {
        for (Element defElmt : def.getElements()) {
            if (elmtAdv[atkElmt][defElmt] > res)
                res = elmtAdv[atkElmt][defElmt];
        }
    }

    return res;
}

double countSkillPower(Engimon e) {
    double res = 0;

    for (Skill s : e.getSkills()) {
        res += s.getBasePower() * s.getMasteryLevel();
    }

    return res;
}

Engimon handleBattle(Engimon e1, Engimon e2) {
    double powerE1 = e1.getLevel() * countElmtAdvPower(e1, e2) + countSkillPower(e1);
    double powerE2 = e2.getLevel() * countElmtAdvPower(e2, e1) + countSkillPower(e2);

    cout << "Power of the e1: " << powerE1 << endl;
    cout << "VS" << endl;
    cout << "Power of the e2: " << powerE2 << endl;

    return (powerE1 >= powerE2) ? e1 : e2;
}

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