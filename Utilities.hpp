#ifndef UTILITIES_HPP
#define UTILITIES_HPP
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

// The player

/* Global Functions */
template <class T>
void printVector(vector<T> const& v, string delimiter, bool newLine, bool showIndex);

void initElmtAdv();

void initPeta(string filePath);

void spawnWildEngimons();

bool isCellOccupied(Position p);

EngimonLiar* getEngimonInCell(Position p);

void printEngimonInPeta(EngimonLiar e);

void printPeta();

double countElmtAdvPower(Engimon atk, Engimon def);

double countSkillPower(Engimon e);

int handleBattle(Engimon e1, Engimon e2);

template <class T>
void move(T obj, Direction dir);

#endif