#ifndef ENGIMONLIAR_HPP
#define ENGIMONLIAR_HPP

#include "Species.hpp"
#include "Element.hpp"
#include "Engimon.hpp"
#include "Position.hpp"
#include <vector>

class EngimonLiar:public Engimon{
  private:
    Position position;
  public:
    Position getPosition() const;
    void setPosition(Position p);
    EngimonLiar(const Species &sp, Position pos, int level);
};

#endif