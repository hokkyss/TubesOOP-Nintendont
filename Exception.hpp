#ifndef EXCEPTION_HPP
#define EXCEPTION_HPP

#include <exception>

using namespace std;

class IndexInvalidException : exception
{
	const char* what() const throw()
	{
		return "Index invalid";
	}
};

class ItemNotFoundException : exception
{
	const char* what() const throw()
	{
		return "Item not found!";
	}
};

class LevelNotEnoughException : exception
{
    const char* what() const throw()
    {
        return "Parent's level is not enough!";
    }
};

class RunOutOfEngimonException : exception
{
    const char* what() const throw()
    {
        return "You have run out of Engimon to use!";
    }
};


class SkillNotCompatibleException : exception
{
	const char* what() const throw()
	{
		return "Skill not compatible!";
	}
};

class SkillExistException : exception
{
  const char* what() const throw()
	{
		return "Skill already exist!";
	}
};

class ParentsInvalidException : exception
{
	const char* what() const throw()
	{
		return "Parents Invalid!";
	}
};

class ItemAlreadyExistedException : exception
{
	const char* what() const throw()
	{
		return "Item already owned!";
	}
};

class InventoryFullException : exception
{
	const char* what() const throw()
	{
		return "Inventory is full!";
	}
};

class EngimonNotFoundException : exception
{
	const char* what() const throw()
	{
		return "Engimon not found!";
	}
};


#endif