#include "Position.hpp"

Position::Position() : x(0), y(0) {}

Position::Position(int x, int y){
  this->x = x;
  this->y = y;
}

int Position::getX(){
  return this->x;
}

int Position::getY(){
  return this->y;
}

void Position::setX(int x){
  this->x = x;
}

void Position::setY(int y){
  this->y = y;
}

void Position::moveBy(int x, int y){
  this->x = this->x+x;
  this->y = this->y+y;
}

void Position::set(int x, int y){
  this->x = x;
  this->y = y;
}

bool Position::operator==(Position p) {
    return (this->x == p.getX() && this->y == p.getY());
}