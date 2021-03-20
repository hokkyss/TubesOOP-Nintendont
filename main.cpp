/* Libraries */
#include <fstream>
#include <iostream>
#include <map>
#include <string>

/* Classes */
#include "Element.hpp"
#include "Engimon.hpp"
#include "Player.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include "Inventory.hpp"

using namespace std;

/* Static Data */
map <string, map<string, double>> elementAdvantages;
// Element Advantages Format
// elementAdvantages[Element Attacker][Element Defender]

/* Global Functions */
void initElementAdvantages() {
    elementAdvantages["Fire"]["Fire"] = 1;
    elementAdvantages["Fire"]["Water"] = 0;
    elementAdvantages["Fire"]["Electric"] = 1;
    elementAdvantages["Fire"]["Ground"] = 0.5;
    elementAdvantages["Fire"]["Ice"] = 2;

    elementAdvantages["Water"]["Fire"] = 2;
    elementAdvantages["Water"]["Water"] = 1;
    elementAdvantages["Water"]["Electric"] = 0;
    elementAdvantages["Water"]["Ground"] = 1;
    elementAdvantages["Water"]["Ice"] = 1;

    elementAdvantages["Electric"]["Fire"] = 1;
    elementAdvantages["Electric"]["Water"] = 2;
    elementAdvantages["Electric"]["Electric"] = 1;
    elementAdvantages["Electric"]["Ground"] = 0;
    elementAdvantages["Electric"]["Ice"] = 1.5;

    elementAdvantages["Ground"]["Fire"] = 1.5;
    elementAdvantages["Ground"]["Water"] = 1;
    elementAdvantages["Ground"]["Electric"] = 2;
    elementAdvantages["Ground"]["Ground"] = 1;
    elementAdvantages["Ground"]["Ice"] = 0;

    elementAdvantages["Ice"]["Fire"] = 0;
    elementAdvantages["Ice"]["Water"] = 1;
    elementAdvantages["Ice"]["Electric"] = 0.5;
    elementAdvantages["Ice"]["Ground"] = 2;
    elementAdvantages["Ice"]["Ice"] = 1;
}

Engimon handleBattle(Engimon e1, Engimon e2) {
    double powerE1 = 1;
    double powerE2 = 2;
}

int main() {

}