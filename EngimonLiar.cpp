#include "Species.hpp"
#include "Element.hpp"
#include "Engimon.hpp"
#include "EngimonLiar.hpp"
#include <bits/stdc++.h>
using namespace std;

EngimonLiar::EngimonLiar(const Species& sp, Position pos, int level) : Engimon(sp.getName(), sp, level), position(pos){}
