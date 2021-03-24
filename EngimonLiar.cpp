#include "EngimonLiar.hpp"
#include <bits/stdc++.h>
using namespace std;

EngimonLiar::EngimonLiar(const Species& sp, Position pos, int level) : Engimon(sp.getName(), sp, level), position(pos){}

Position EngimonLiar::getPosition() const { return position; }

void EngimonLiar::setPosition(Position p) {
    this->position.setX(p.getX());
    this->position.setY(p.getY());
}