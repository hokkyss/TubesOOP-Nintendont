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

using namespace std;

/* Static Data */
map<string, map<string, double>> elmtAdv;
// Element Advantages Table
// Format Input & Access: elmtAdv[Element Attacker][Element Defender]

vector<vector<char>> peta;
// Matriks Peta
// Format Input in file.txt (no space):
// -----ooo
// --oooo--
// ---oo---
// --oo----
// --oooo--
// ---oo---
// ---o----
// --------
// Access: peta[i][j] if (0 < i < peta.size() and 0 < j < peta[i].size() )

/* Global Functions */
void initElmtAdv() {
    elmtAdv["Fire"]["Fire"] = 1;
    elmtAdv["Fire"]["Water"] = 0;
    elmtAdv["Fire"]["Electric"] = 1;
    elmtAdv["Fire"]["Ground"] = 0.5;
    elmtAdv["Fire"]["Ice"] = 2;

    elmtAdv["Water"]["Fire"] = 2;
    elmtAdv["Water"]["Water"] = 1;
    elmtAdv["Water"]["Electric"] = 0;
    elmtAdv["Water"]["Ground"] = 1;
    elmtAdv["Water"]["Ice"] = 1;

    elmtAdv["Electric"]["Fire"] = 1;
    elmtAdv["Electric"]["Water"] = 2;
    elmtAdv["Electric"]["Electric"] = 1;
    elmtAdv["Electric"]["Ground"] = 0;
    elmtAdv["Electric"]["Ice"] = 1.5;

    elmtAdv["Ground"]["Fire"] = 1.5;
    elmtAdv["Ground"]["Water"] = 1;
    elmtAdv["Ground"]["Electric"] = 2;
    elmtAdv["Ground"]["Ground"] = 1;
    elmtAdv["Ground"]["Ice"] = 0;

    elmtAdv["Ice"]["Fire"] = 0;
    elmtAdv["Ice"]["Water"] = 1;
    elmtAdv["Ice"]["Electric"] = 0.5;
    elmtAdv["Ice"]["Ground"] = 2;
    elmtAdv["Ice"]["Ice"] = 1;
}

void initPeta(string filePath) {
    ifstream petaFile(filePath);
    string textLine;

    while (getline(petaFile, textLine))
    {
        vector<char> rowPeta;

        for (char c : textLine)
        {
            rowPeta.push_back(c);
        }

        peta.push_back(rowPeta);
    }
}

void printPeta() {
    for (int i = 0; i < peta.size(); i++) {
        for (int j = 0; j < peta[i].size(); j++) {
            cout << peta[i][j] << " ";
        }

        cout << endl;
    }
}

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