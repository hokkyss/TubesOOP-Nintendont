#ifndef ENGIMONLIAR_HPP
#define ENGIMONLIAR_HPP

#include "Species.hpp"
#include "Element.hpp"
#include "Engimon.hpp"
#include "Position.hpp"
#include <vector>

class EngimonLiar:public Engimon{
  public:
    Position position;
    EngimonLiar(const Species& sp, Position pos);
};

#endif