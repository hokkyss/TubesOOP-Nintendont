package com.nintendont.game.entities;

import com.nintendont.game.exceptions.InputTooLargeException;
import com.nintendont.game.exceptions.ItemNotFoundException;
import com.nintendont.game.comparators.EngimonComparator;
import com.nintendont.game.comparators.SkillItemComparator;
import com.nintendont.game.Logger;
import java.util.*;

public class Inventory<T> {
    // atribut Inventory
    public static final int maxCapacity = 20;

    public static int nCapacity = 0;
    public ArrayList<T> invenList;
    public Map<T, Integer> countInven;

    public Inventory() {
        invenList = new ArrayList<>();
        countInven = new HashMap<>();
    }

    // getter
    public T get(int i) {
        return invenList.get(i);
    }

    public Integer getCount(T el) {
        return countInven.get(el);
    }

    // setter
    public void insert(T el) // throws InputTooLargeException
    {
        try {
            insert(el, 1);
        } catch (InputTooLargeException err) {
            err.printStackTrace();
            // throw err; // bagusnya do nothing
        }
    }

    public void insert(T el, int count) throws InputTooLargeException {
        if (nCapacity + count <= maxCapacity) {
            if (countInven.containsKey(el)) {
                countInven.put(el, countInven.get(el) + count);
            } else {
                invenList.add(el);
                countInven.put(el, count);
            }
            nCapacity += count;
        } else {
            throw new InputTooLargeException();
        }
    }

    public void remove(T el) throws // InputTooLargeException,
            ItemNotFoundException {
        try{
            remove(el, 1);
        }catch (ItemNotFoundException err){
            throw err;
        }catch (InputTooLargeException err){
            // do nothing throw err;
        }
    }

    public void remove(T el, int count) throws InputTooLargeException, ItemNotFoundException {
        if (countInven.containsKey(el)) {
            if (countInven.get(el) >= count) {
                if (countInven.get(el) == count) {
                    countInven.remove(el);
                    invenList.remove(el);
                } else {
                    countInven.put(el, countInven.get(el) - count);
                }
            } else {
                throw new InputTooLargeException();
            }
        } else {
            throw new ItemNotFoundException();
        }
    }

    public static boolean isFull(){
        return nCapacity == maxCapacity;
    }

    public int find(T el){
        return invenList.indexOf(el);
    }

    public int size(){
        return invenList.size();
    }

    public String toString() {
        String s = "";

        for (T el : invenList) {
            s = s + ("{\nelement:{\n" + el.toString() + "\n},\ncount:" + countInven.get(el) + "\n}");

            if (!el.equals(invenList.get(invenList.size() - 1)))
                s = s + ",\n";
            else
                s = s + "\n";
        }

        return s;
    }

    public static void sortInventory(Inventory<SkillItem> al, boolean type) {
        al.invenList.sort(new SkillItemComparator());

        al.invenList.stream().forEach(el -> Logger
                .print(el.containedSkill.skillName + "-" + el.containedSkill.basePower + "-" + al.countInven.get(el)));
    }

    public static void sortInventory(Inventory<Engimon> al) {
        al.invenList.sort(new EngimonComparator());

        al.invenList.stream().forEach(el -> Logger.print(el.getName()));
    }
}
