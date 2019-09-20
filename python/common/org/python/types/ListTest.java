package org.python.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;


public class ListTest {

    private List createList(int ...values) {
        List l = new List();
        for (int value : values) {
            l.append(Int.getInt(value));
        }
        return l;
    }

    private List createList(org.python.types.Object ...values) {
        List l = new List();
        for (org.python.types.Object value : values) {
            l.append(value);
        }
        return l;
    }

    @Test
    public void SetItemInteger() {
        List l = createList(1);

        l.__setitem__(Int.getInt(0), Int.getInt(5));
        assertEquals(Int.getInt(5), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void SetItemString() {
        List l = createList(1, 2, 3);

        l.__setitem__(Int.getInt(1), new Str("hello"));
        l.__setitem__(Int.getInt(2), new Str("there"));
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("hello"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("there"), l.__getitem__(Int.getInt(2)));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void SetItemOutOfBound() {
        List l = new List();
        l.__setitem__(Int.getInt(0), Int.getInt(5));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void SetItemOutOfBoundsNegativeIndex() {
        List l = createList(1);

        l.__setitem__(Int.getInt(-2), Int.getInt(5));
    }

    @Test
    public void DelItem() {
        List l = createList(1, 2, 3);

        l.__delitem__(Int.getInt(1));
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(3), l.__getitem__(Int.getInt(1)));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void DelItemOutOfBounds() {
        List l = new List();
        l.__delitem__(Int.getInt(0));
    }

    @Test
    public void Iterator() {
        List l = new List();
        org.python.Object iter = l.__iter__();
        assertTrue(iter instanceof List_Iterator);
    }

    @Test
    public void Reversed() {
        List l = new List();
        org.python.Object reversedIter = l.__reversed__();
        assertTrue(reversedIter instanceof List_ReverseIterator);
    }

    @Test
    public void Contains() {
        List l = createList(1, 2, 3, 4, 5);

        assertTrue(l.__contains__(Int.getInt(1)).toBoolean());
    }

    @Test
    public void ContainsDoesntExist() {
        List l = createList(1, 2, 3, 4, 5);

        assertFalse(l.__contains__(Int.getInt(0)).toBoolean());
    }

    @Test
    public void ContainsBool() {
        List l = new List();
        l.append(Bool.getBool(true));
        l.append(Bool.getBool(false));

        // Since True=1 and False=0
        assertTrue(l.__contains__(Int.getInt(1)).toBoolean());
        assertTrue(l.__contains__(Int.getInt(0)).toBoolean());
        assertFalse(l.__contains__(Int.getInt(2)).toBoolean());
    }

    @Test
    public void Add() {
        List l = new List();
        l.append(Int.getInt(1));
        List m = new List();
        m.append(Int.getInt(2));
        List n = (List) l.__add__(m);

        assertEquals(Int.getInt(1), n.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), n.__getitem__(Int.getInt(1)));
    }

    @Test
    public void MulWithInteger() {
        List l = createList(1, 2, 3);

        List m = (List) l.__mul__(Int.getInt(2));

        assertEquals(m.__getitem__(Int.getInt(0)), m.__getitem__(Int.getInt(3)));
        assertEquals(m.__getitem__(Int.getInt(1)), m.__getitem__(Int.getInt(4)));
        assertEquals(m.__getitem__(Int.getInt(2)), m.__getitem__(Int.getInt(5)));
    }

    @Test
    public void MulEmpty() {
        List l = new List();

        List mul = (List) l.__mul__(Int.getInt(2));

        assertEquals(Int.getInt(0), mul.__len__());
    }

    @Test
    public void MulBoolTrue() {
        List l = createList(1, 2);

        List mulTrue = (List) l.__mul__(Bool.getBool(true));

        assertEquals(Int.getInt(1), mulTrue.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), mulTrue.__getitem__(Int.getInt(1)));
    }

    @Test
    public void MulBoolFalse() {
        List l = createList(1, 2);

        List mulFalse = (List) l.__mul__(Bool.getBool(false));

        assertEquals(Int.getInt(0), mulFalse.__len__());
    }

    @Test
    public void IMulWithInteger() {
        List l = createList(1, 2, 3);

        List m = (List) l.__imul__(Int.getInt(2));

        assertEquals(m.__getitem__(Int.getInt(0)), m.__getitem__(Int.getInt(3)));
        assertEquals(m.__getitem__(Int.getInt(1)), m.__getitem__(Int.getInt(4)));
        assertEquals(m.__getitem__(Int.getInt(2)), m.__getitem__(Int.getInt(5)));
    }

    @Test
    public void IMulEmpty() {
        List l = new List();

        List mul = (List) l.__imul__(Int.getInt(2));

        assertEquals(Int.getInt(0), mul.__len__());
    }

    @Test
    public void IMulBoolTrue() {
        List l = createList(1, 2);

        List mulTrue = (List) l.__imul__(Bool.getBool(true));

        assertEquals(Int.getInt(1), mulTrue.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), mulTrue.__getitem__(Int.getInt(1)));
    }

    @Test
    public void IMulBoolFalse() {
        List l = createList(1, 2);

        List mulFalse = (List) l.__imul__(Bool.getBool(false));

        assertEquals(Int.getInt(0), mulFalse.__len__());
    }

    @Test
    public void Append() {
        List l = new List();
        l.append(Int.getInt(1));
        l.append(new Str("a string"));
        l.append(Bool.getBool(true));

        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a string"), l.__getitem__(Int.getInt(1)));
        assertEquals(Bool.getBool(true), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void Clear() {
        List l = new List();
        l.append(Int.getInt(1));
        l.append(new Str("a string"));
        l.append(Bool.getBool(true));

        assertEquals(Int.getInt(3), l.__len__());
        l.clear();
        assertEquals(Int.getInt(0), l.__len__());
    }

    @Test
    public void ClearEmpty() {
        List l = new List();

        assertEquals(Int.getInt(0), l.__len__());
        l.clear();
        assertEquals(Int.getInt(0), l.__len__());
    }

    @Test
    public void Copy() {
        List l = new List();
        l.append(Int.getInt(1));
        l.append(new Str("a string"));
        l.append(Bool.getBool(true));

        List lCopy = (List) l.copy();

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
    public void Count() {
        List l = new List();
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
    public void ExtendList() {
        List l = new List();
        l.append(Int.getInt(1));

        List toExtend = new List();
        l.append(new Str("a"));
        l.append(new Str("b"));

        l.extend(toExtend);
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("b"), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void ExtendTuple() {
        List l = new List();
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
    public void ExtendSet() {
        List l = new List();
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
    public void ExtendFrozenSet() {
        List l = new List();
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
    public void ExtendDict() {
        List l = new List();
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
    public void Index() {
        List l = new List();
        l.append(new Str("a"));
        l.append(new Str("b"));
        l.append(new Str("c"));

        assertEquals(Int.getInt(0), l.index(new Str("a"), null, null));
    }

    @Test
    public void IndexStart() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(1), l.index(Int.getInt(2), Int.getInt(1), null));
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void IndexStartAfterValue() {
        List l = createList(1, 2, 3);

        l.index(Int.getInt(1), Int.getInt(1), null);
    }

    @Test
    public void IndexStartEnd() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(1), l.index(Int.getInt(2), Int.getInt(0), l.__len__()));
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void IndexStartEndBeforeValue() {
        List l = createList(1, 2, 3);

        l.index(Int.getInt(3), Int.getInt(0), Int.getInt(2));
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void IndexNotFound() {
        List l = createList(1, 2, 3);

        l.index(Int.getInt(5), null, null);
    }

    @Test
    public void InsertFirst() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(0), Int.getInt(4));
        assertEquals(Int.getInt(4), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void InsertSecond() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(1), Int.getInt(5));
        assertEquals(Int.getInt(5), l.__getitem__(Int.getInt(1)));
    }

    @Test
    public void InsertLast() {
        List l = createList(1, 2, 3);

        l.insert(l.__len__(), Int.getInt(6));
        assertEquals(Int.getInt(6), l.__getitem__(Int.getInt(3)));
    }

    @Test
    public void InsertAboveLast() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(200), Int.getInt(7));
        assertEquals(Int.getInt(7), l.__getitem__(Int.getInt(3)));
    }

    @Test
    public void InsertNegativeOne() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(-1), Int.getInt(8));
        assertEquals(Int.getInt(8), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void InsertNegativeLength() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(-l.__len__().value), Int.getInt(9));
        assertEquals(Int.getInt(9), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void InsertNegativeAbove() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(-200), Int.getInt(10));
        assertEquals(Int.getInt(10), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void InsertString() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(0), new Str("hello"));
        assertEquals(new Str("hello"), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void InsertList() {
        List l = createList(1, 2, 3);

        List innerList = createList(1, 2);

        l.insert(Int.getInt(0), innerList);
        assertEquals(createList(1, 2), l.__getitem__(Int.getInt(0)));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void InsertCharIndex() {
        List l = createList(1, 2, 3);

        l.insert(new Str('a'), Int.getInt(4));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void InsertListIndex() {
        List l = createList(1, 2, 3);

        l.insert(createList(1, 2, 3), Int.getInt(4));
    }

    @Test
    public void PopNoIndex() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(3), l.pop(null));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test
    public void PopZeroIndex() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(1), l.pop(Int.getInt(0)));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test
    public void PopNegativeIndex() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(2), l.pop(Int.getInt(-2)));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void PopEmptyList() {
        List l = new List();

        l.pop(null);
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void PopIndexTooLarge() {
        List l = createList(1, 2, 3);

        l.pop(Int.getInt(3));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void PopIndexTooSmall() {
        List l = createList(1, 2, 3);

        l.pop(Int.getInt(-4));
    }

    @Test
    public void RemoveInteger() {
        List l = createList(1, 2, 3);
        l.remove(Int.getInt(1));

        assertEquals(createList(2, 3), l);
    }

    @Test
    public void RemoveFirstDuplicate() {
        List l = createList(1, 2, 2, 3, 2);
        l.remove(Int.getInt(2));

        assertEquals(createList(1, 2, 3, 2), l);
    }

    @Test
    public void RemoveBoolean() {
        List l = createList(Bool.TRUE, Bool.FALSE, Bool.TRUE, Bool.FALSE);
        l.remove(Int.getInt(1));

        assertEquals(createList(Bool.FALSE, Bool.TRUE, Bool.FALSE), l);
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void RemoveNotInList() {
        List l = createList(1, 2);
        l.remove(Int.getInt(3));
    }

    @Test
    public void Reverse() {
        List l = createList(1, 2, 3, 4, 5);
        l.reverse();

        assertEquals(createList(5, 4, 3, 2, 1), l);
    }

    @Test
    public void SortInOrder() {
        List l1 = createList(9, 4, 7);
        List l2 = createList(new Str("beta"), new Str("theta"), new Str("alpha"));

        l1.sort(null, null);
        l2.sort(null, null);

        assertEquals(createList(4, 7, 9), l1);
        assertEquals(createList(new Str("alpha"), new Str("beta"), new Str("theta")), l2);
    }

    @Test
    public void SortReverse() {
        List l1 = createList(9, 4, 7);
        List l2 = createList(new Str("beta"), new Str("theta"), new Str("alpha"));

        l1.sort(null, Bool.TRUE);
        l2.sort(null, Bool.TRUE);

        assertEquals(createList(9, 7, 4), l1);
        assertEquals(createList(new Str("theta"), new Str("beta"), new Str("alpha")), l2);
    }

    public static org.python.Object second(org.python.Object obj) {
        Str str = (Str) obj;
        return str.__getitem__(Int.getInt(1));
    }

    private static org.python.types.Function getFunctionSecond() throws NoSuchMethodException {
        java.lang.String[] args = {"obj"};
        java.lang.String[] default_args = {};
        java.lang.String[] kwonlyargs = {};

        java.lang.Class listTestClass = ListTest.class;
        java.lang.reflect.Method method = listTestClass.getMethod("second", org.python.Object.class);
        final Function f = new Function(method, args, default_args, null, kwonlyargs, null);
        return f;
    }

    @Test
    public void SortKey() {
        List l = createList(new Str("abc"), new Str("bza"), new Str("cda"), new Str("daa"));

        try {
            final Function f = ListTest.getFunctionSecond();
            l.sort((org.python.Object) f, null);

            assertEquals(createList(new Str("daa"), new Str("abc"), new Str("cda"), new Str("bza")), l);
        } catch (NoSuchMethodException e) {
            assertTrue(false);
        }
    }

    @Test
    public void SortKeyReversed() {
        List l = createList(new Str("abc"), new Str("bza"), new Str("cda"), new Str("daa"));

        try {
            final Function f = ListTest.getFunctionSecond();
            l.sort((org.python.Object) f, Bool.TRUE);

            assertEquals(createList(new Str("bza"), new Str("cda"), new Str("abc"), new Str("daa")), l);
        } catch (NoSuchMethodException e) {
            assertTrue(false);
        }
    }
}
