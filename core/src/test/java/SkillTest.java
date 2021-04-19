import org.junit.Test;
import org.junit.Assert;

import com.nintendont.game.entities.Skill;
import com.nintendont.game.comparators.SkillComparator;

import java.util.PriorityQueue;

public class SkillTest {
    @Test
    public void test_mastery() {
        Skill skill1 = new Skill(Skill.FLAMETHROWER);
        Assert.assertEquals(1, skill1.getMasteryLevel());

        skill1.increaseMasteryLevel();
        skill1.increaseMasteryLevel();
        skill1.increaseMasteryLevel();
        Assert.assertEquals(3, skill1.getMasteryLevel());
    }

    @Test
    public void test_set_mastery() {
        Skill skill1 = new Skill(Skill.ICEBEAM);

        skill1.setMasteryLevel((byte)100);
        Assert.assertEquals(3, skill1.getMasteryLevel());

        skill1.setMasteryLevel((byte)-5);
        Assert.assertEquals(1, skill1.getMasteryLevel());
    }

    @Test
    public void test_comparator() {
        Skill skill1 = new Skill(Skill.FLAMETHROWER);
        skill1.increaseMasteryLevel();
        Assert.assertEquals(2, skill1.getMasteryLevel());

        Skill skill2 = new Skill(Skill.THOUSANDARROWS);
        skill2.increaseMasteryLevel();
        Assert.assertEquals(2, skill2.getMasteryLevel());
        skill2.increaseMasteryLevel();
        Assert.assertEquals(3, skill2.getMasteryLevel());

        PriorityQueue<Skill> pq = new PriorityQueue<>(new SkillComparator());

        pq.add(skill1);
        pq.add(skill2);

        Assert.assertEquals(2, pq.size());
        Skill top = pq.poll();
        Skill top2 = pq.poll();
        Assert.assertEquals(3, top.getMasteryLevel());
        Assert.assertEquals(2, top2.getMasteryLevel());
    }

    @Test
    public void test_learnableBy() {
        Skill skill1 = new Skill(Skill.FLAMETHROWER);
        Skill skill2 = new Skill(Skill.FIREPLEDGE);

        Assert.assertEquals(skill1.learnableBy, skill2.learnableBy);
    }

    @Test
    public void test_equals()
    {
        Skill skill1 = new Skill(Skill.FLAMETHROWER);
        Skill skill2 = new Skill(Skill.FLAMETHROWER);

        skill1.increaseMasteryLevel();
        Assert.assertTrue(skill1.equals(skill2));
        Assert.assertTrue(skill2.equals(skill1));

        Skill skill3 = new Skill(Skill.FIREPLEDGE);
        Assert.assertFalse(skill1.equals(skill3));
        Assert.assertFalse(skill2.equals(skill3));
    }
}
