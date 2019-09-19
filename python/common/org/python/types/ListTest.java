package org.python.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ListTest {

    @Test
    public void testSetItemInteger() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.__setitem__(Int.getInt(0), Int.getInt(5));
        assertEquals(Int.getInt(5), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void testSetItemString() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        l.__setitem__(Int.getInt(1), new Str("hello"));
        l.__setitem__(Int.getInt(2), new Str("there"));
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("hello"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("there"), l.__getitem__(Int.getInt(2)));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void testSetItemOutOfBound() {
        org.python.types.List l = new List();
        l.__setitem__(Int.getInt(0), Int.getInt(5));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void testSetItemOutOfBoundsNegativeIndex() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.__setitem__(Int.getInt(-2), Int.getInt(5));
    }

    @Test
    public void testDelItem() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        l.__delitem__(Int.getInt(1));
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(3), l.__getitem__(Int.getInt(1)));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void testDelItemOutOfBounds() {
        org.python.types.List l = new List();
        l.__delitem__(Int.getInt(0));
    }

    @Test
    public void testIterator() {
        org.python.types.List l = new List();
        org.python.Object iter = l.__iter__();
        assertTrue(iter instanceof List_Iterator);
    }

    @Test
    public void testReversed() {
        org.python.types.List l = new List();
        org.python.Object reversedIter = l.__reversed__();
        assertTrue(reversedIter instanceof List_ReverseIterator);
    }

    @Test
    public void testContains() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        l.append(Int.getInt(4));
        l.append(Int.getInt(5));

        assertTrue(l.__contains__(Int.getInt(1)).toBoolean());
    }

    @Test
    public void testContainsDoesntExist() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        l.append(Int.getInt(4));
        l.append(Int.getInt(5));

        assertFalse(l.__contains__(Int.getInt(0)).toBoolean());
    }

    @Test
    public void testContainsBool() {
        org.python.types.List l = new List();
        l.append(Bool.getBool(true));
        l.append(Bool.getBool(false));

        // Since True=1 and False=0
        assertTrue(l.__contains__(Int.getInt(1)).toBoolean());
        assertTrue(l.__contains__(Int.getInt(0)).toBoolean());
        assertFalse(l.__contains__(Int.getInt(2)).toBoolean());
    }

    @Test
    public void testAdd() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        org.python.types.List m = new List();
        m.append(Int.getInt(2));
        org.python.types.List n = (List) l.__add__(m);

        assertEquals(Int.getInt(1), n.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), n.__getitem__(Int.getInt(1)));
    }

    @Test
    public void testMulWithInteger() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        org.python.types.List m = (List) l.__mul__(Int.getInt(2));

        assertEquals(m.__getitem__(Int.getInt(0)), m.__getitem__(Int.getInt(3)));
        assertEquals(m.__getitem__(Int.getInt(1)), m.__getitem__(Int.getInt(4)));
        assertEquals(m.__getitem__(Int.getInt(2)), m.__getitem__(Int.getInt(5)));
    }

    @Test
    public void testMulEmpty() {
        org.python.types.List l = new List();

        List mul = (List) l.__mul__(Int.getInt(2));

        assertEquals(Int.getInt(0), mul.__len__());
    }

    @Test
    public void testMulBoolTrue() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));

        List mulTrue = (List) l.__mul__(Bool.getBool(true));

        assertEquals(Int.getInt(1), mulTrue.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), mulTrue.__getitem__(Int.getInt(1)));
    }

    @Test
    public void testMulBoolFalse() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));

        List mulFalse = (List) l.__mul__(Bool.getBool(false));

        assertEquals(Int.getInt(0), mulFalse.__len__());
    }

    @Test
    public void testIMulWithInteger() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        org.python.types.List m = (List) l.__imul__(Int.getInt(2));

        assertEquals(m.__getitem__(Int.getInt(0)), m.__getitem__(Int.getInt(3)));
        assertEquals(m.__getitem__(Int.getInt(1)), m.__getitem__(Int.getInt(4)));
        assertEquals(m.__getitem__(Int.getInt(2)), m.__getitem__(Int.getInt(5)));
    }

    @Test
    public void testIMulEmpty() {
        org.python.types.List l = new List();

        List mul = (List) l.__imul__(Int.getInt(2));

        assertEquals(Int.getInt(0), mul.__len__());
    }

    @Test
    public void testIMulBoolTrue() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));

        List mulTrue = (List) l.__imul__(Bool.getBool(true));

        assertEquals(Int.getInt(1), mulTrue.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), mulTrue.__getitem__(Int.getInt(1)));
    }

    @Test
    public void testIMulBoolFalse() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));

        List mulFalse = (List) l.__imul__(Bool.getBool(false));

        assertEquals(Int.getInt(0), mulFalse.__len__());
    }

    @Test
    public void testAppend() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(new Str("a string"));
        l.append(Bool.getBool(true));

        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a string"), l.__getitem__(Int.getInt(1)));
        assertEquals(Bool.getBool(true), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void testClear() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(new Str("a string"));
        l.append(Bool.getBool(true));

        assertEquals(Int.getInt(3), l.__len__());
        l.clear();
        assertEquals(Int.getInt(0), l.__len__());
    }

    @Test
    public void testClearEmpty() {
        org.python.types.List l = new List();

        assertEquals(Int.getInt(0), l.__len__());
        l.clear();
        assertEquals(Int.getInt(0), l.__len__());
    }

    @Test
    public void testCopy() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(new Str("a string"));
        l.append(Bool.getBool(true));

        org.python.types.List lCopy = (List) l.copy();

        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a string"), l.__getitem__(Int.getInt(1)));
        assertEquals(Bool.getBool(true), l.__getitem__(Int.getInt(2)));
        assertEquals(Int.getInt(1), lCopy.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a string"), lCopy.__getitem__(Int.getInt(1)));
        assertEquals(Bool.getBool(true), lCopy.__getitem__(Int.getInt(2)));

        l.__delitem__(Int.getInt(0));
        assertEquals(Int.getInt(2), l.__len__());
        assertEquals(Int.getInt(3), lCopy.__len__());
    }

    @Test
    public void testCount() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(1));
        l.append(new Str("a string"));
        l.append(Bool.getBool(true));

        // Remember, True == 1
        assertEquals(Int.getInt(3), l.count(Int.getInt(1)));
        assertEquals(Int.getInt(1), l.count(new Str("a string")));
        assertEquals(Int.getInt(3), l.count(Bool.getBool(true)));
        assertEquals(Int.getInt(0), l.count(new Str("a non-existing string")));
    }

    @Test
    public void testExtendList() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));

        org.python.types.List toExtend = new List();
        l.append(new Str("a"));
        l.append(new Str("b"));

        l.extend(toExtend);
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("b"), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void testExtendTuple() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));

        java.util.List<org.python.Object> tuple = new java.util.LinkedList<org.python.Object>();
        tuple.add(new Str("a"));
        tuple.add(new Str("b"));
        Tuple toExtend = new Tuple(tuple);

        l.extend(toExtend);
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("b"), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void testExtendSet() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));

        java.util.Set<org.python.Object> set = new java.util.HashSet<org.python.Object>();
        set.add(new Str("a"));
        set.add(new Str("b"));
        Set toExtend = new Set(set);

        l.extend(toExtend);
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("b"), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void testExtendFrozenSet() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));

        java.util.Set<org.python.Object> set = new java.util.HashSet<org.python.Object>();
        set.add(new Str("a"));
        set.add(new Str("b"));
        FrozenSet toExtend = new FrozenSet(set);

        l.extend(toExtend);
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("b"), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void testExtendDict() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));

        Dict toExtend = new Dict();
        toExtend.__setitem__(new Str("a"), Int.getInt(0));
        toExtend.__setitem__(new Str("b"), Int.getInt(0));

        l.extend(toExtend);
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("b"), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void testIndex() {
        org.python.types.List l = new List();
        l.append(new Str("a"));
        l.append(new Str("b"));
        l.append(new Str("c"));

        assertEquals(Int.getInt(0), l.index(new Str("a"), null, null));
    }

    @Test
    public void testIndexStart() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));

        assertEquals(Int.getInt(1), l.index(Int.getInt(2), Int.getInt(1), null));
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void testIndexStartAfterValue() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));

        l.index(Int.getInt(1), Int.getInt(1), null);
    }

    @Test
    public void testIndexStartEnd() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));

        assertEquals(Int.getInt(1), l.index(Int.getInt(2), Int.getInt(0), l.__len__()));
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void testIndexStartEndBeforeValue() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));

        l.index(Int.getInt(3), Int.getInt(0), Int.getInt(2));
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void testIndexNotFound() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));

        l.index(Int.getInt(5), null, null);
    }
}
