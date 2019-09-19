package org.python.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
}

