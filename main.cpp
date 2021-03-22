/* Libraries */
#include <fstream>
#include <iostream>
#include <map>
#include <string>
#include <vector>

/* Classes */
#include "Element.hpp"
#include "Engimon.hpp"
#include "Player.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include "Inventory.hpp"

/* Utilities */
#include "Utilities.cpp"

using namespace std;

double countElmtAdvPower(Engimon atk, Engimon def) {
    double res = -1;
    
    for (string atkElmt : atk.elements) {
        for (string defElmt : def.elements) {
            if (elmtAdv[atkElmt][defElmt] > res)
                res = elmtAdv[atkElmt][defElmt];
        }
    }

    return res;
}

double countSkillPower(Engimon e) {
    double res = 0;

    for (Skill s : e.skills) {
        res += s.getBasePower() * s.getMasteryLevel();
    }

    return res;
}

Engimon handleBattle(Engimon e1, Engimon e2) {
    double powerE1 = e1.level * countElmtAdvPower(e1, e2) + countSkillPower(e1);
    double powerE2 = e2.level * countElmtAdvPower(e2, e1) + countSkillPower(e2);

    cout << "Power of the e1: " << powerE1 << endl;
    cout << "VS" << endl;
    cout << "Power of the e2: " << powerE2 << endl;

    return (powerE1 >= powerE2) ? e1 : e2;
}

int main() {
    /* Const Declaration */
    const string filePath = "./input/contoh.txt";

    /* Initialization Phase */
    initElmtAdv();
    initPeta(filePath);

    /* In Game Phase */
    printPeta();

    return 0;
}