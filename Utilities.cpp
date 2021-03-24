#include <iostream>
#include <fstream>
#include <vector>
#include <map>
#include <string>
#include <stdlib.h>
#include <algorithm>

#include "Element.hpp"
#include "Engimon.hpp"
#include "EngimonLiar.hpp"
#include "Species.hpp"
#include "Player.hpp"
#include "Direction.hpp"

using namespace std;

#define MAX_WILD_ENGIMON 10
#define LEVEL_BIG_WILD_POKEMON 15

/* Static Data */
double elmtAdv[5][5];
// Element Advantages Table
// Format Input & Access: elmtAdv[Element Attacker][Element Defender]

vector<vector<char>> tabPeta;
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
// Access: tabPeta[i][j] if (0 < i < tabPeta.size() and 0 < j < tabPeta[i].size() )

/* Dynamic Data */
EngimonLiar* wildEngimons[MAX_WILD_ENGIMON];
// Array of Engimon Liar

Player player;
// The player

/* Global Functions */
template <class T>
void printVector(vector<T> const& v, string delimiter, bool newLine, bool showIndex) {
    for(int i = 0; i < v.size(); i++) {
        if (showIndex) cout << i+1 << "." << endl;
        cout << v[i];
        if(i != v.size() - 1){
            cout << delimiter;
        }
        if (newLine) cout << endl;
    }
}

void initElmtAdv() {
    elmtAdv[Fire][Fire] = 1;
    elmtAdv[Fire][Water] = 0;
    elmtAdv[Fire][Electric] = 1;
    elmtAdv[Fire][Ground] = 0.5;
    elmtAdv[Fire][Ice] = 2;

    elmtAdv[Water][Fire] = 2;
    elmtAdv[Water][Water] = 1;
    elmtAdv[Water][Electric] = 0;
    elmtAdv[Water][Ground] = 1;
    elmtAdv[Water][Ice] = 1;

    elmtAdv[Electric][Fire] = 1;
    elmtAdv[Electric][Water] = 2;
    elmtAdv[Electric][Electric] = 1;
    elmtAdv[Electric][Ground] = 0;
    elmtAdv[Electric][Ice] = 1.5;

    elmtAdv[Ground][Fire] = 1.5;
    elmtAdv[Ground][Water] = 1;
    elmtAdv[Ground][Electric] = 2;
    elmtAdv[Ground][Ground] = 1;
    elmtAdv[Ground][Ice] = 0;

    elmtAdv[Ice][Fire] = 0;
    elmtAdv[Ice][Water] = 1;
    elmtAdv[Ice][Electric] = 0.5;
    elmtAdv[Ice][Ground] = 2;
    elmtAdv[Ice][Ice] = 1;
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

        tabPeta.push_back(rowPeta);
    }
}

void spawnWildEngimons() {
    srand(time(NULL));
    for (int i = 0; i < MAX_WILD_ENGIMON; i++) {
        Species s = listOfSpecies[rand() % listOfSpecies.size()];
        Position p;
        
        do {
            p = Position(rand() % tabPeta[0].size(), rand() % tabPeta.size());
        } while (isCellOccupied(p));

        wildEngimons[i] = new EngimonLiar(s, p, (rand() % player.getActiveEngimon().getLevel() * 2) + 1);
    }
}

bool isCellOccupied(Position p) {
    if (player.getPosition() == p)
        return true;

    for (EngimonLiar* e : wildEngimons)
    {
        if (e != NULL) {
            if (e->getPosition() == p)
                return true;
        }
    }

    return false;
}

EngimonLiar* getEngimonInCell(Position p) {
    for (EngimonLiar* e : wildEngimons)
    {
        if (e != NULL) {
            if (e->getPosition() == p)
                return e;
        }
    }

    return NULL;
}

void printEngimonInPeta(EngimonLiar e) {
    vector<Element> elmts = e.getElements();
    if (elmts.size() >= 2) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "S";
        else
            cout << "s";
    } else if (find(elmts.begin(), elmts.end(), Water) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "W";
        else
            cout << "w";
    } else if (find(elmts.begin(), elmts.end(), Fire) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "F";
        else
            cout << "f";
    } else if (find(elmts.begin(), elmts.end(), Ice) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "I";
        else
            cout << "i";
    } else if (find(elmts.begin(), elmts.end(), Ground) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "G";
        else
            cout << "g";
    } else if (find(elmts.begin(), elmts.end(), Electric) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "E";
        else
            cout << "e";
    }
}

void printPeta() {
    for (int i = 0; i < tabPeta.size(); i++) {
        for (int j = 0; j < tabPeta[i].size(); j++) {
            EngimonLiar* e = getEngimonInCell(Position(j, i));

            if (player.getPosition() == Position(j, i))
                cout << "P";
            else if (e != NULL)
                printEngimonInPeta(*e);
            else
                cout << tabPeta[i][j];
        }

        cout << endl;
    }
}

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

int handleBattle(Engimon e1, Engimon e2) {
    double powerE1 = e1.getLevel() * countElmtAdvPower(e1, e2) + countSkillPower(e1);
    double powerE2 = e2.getLevel() * countElmtAdvPower(e2, e1) + countSkillPower(e2);

    cout << "Power of the e1: " << powerE1 << endl;
    cout << "VS" << endl;
    cout << "Power of the e2: " << powerE2 << endl;

    return (powerE1 >= powerE2) ? 1 : 2;
}

template <class T>
void move(T obj, Direction dir) {
    if (dir == Up) {
        obj.setPosition(obj.getPosition().getX(), obj.getPosition().getY() - 1);
    } else if (dir == Down) {
        obj.setPosition(obj.getPosition().getX(), obj.getPosition().getY() + 1);
    } else if (dir == Left) {
        obj.setPosition(obj.getPosition().getX() - 1, obj.getPosition().getY());
    } else if (dir == Right) {
        obj.setPosition(obj.getPosition().getX() + 1, obj.getPosition().getY());
    }
}