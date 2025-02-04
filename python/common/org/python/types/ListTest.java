package org.python.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import org.junit.Test;


public class ListTest {
    @Test
    public void toJava_notNull() {
        assertNotNull(new List().toJava());
    }

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
    public void __setitem__integer() {
        List l = createList(1);

        l.__setitem__(Int.getInt(0), Int.getInt(5));
        assertEquals(Int.getInt(5), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void __setitem__string() {
        List l = createList(1, 2, 3);

        l.__setitem__(Int.getInt(1), new Str("hello"));
        l.__setitem__(Int.getInt(2), new Str("there"));
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("hello"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("there"), l.__getitem__(Int.getInt(2)));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void __setitem__outOfBound() {
        List l = new List();
        l.__setitem__(Int.getInt(0), Int.getInt(5));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void __setitem__outOfBoundsNegativeIndex() {
        List l = createList(1);

        l.__setitem__(Int.getInt(-2), Int.getInt(5));
    }

    @Test
    public void __delitem__() {
        List l = createList(1, 2, 3);

        l.__delitem__(Int.getInt(1));
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(3), l.__getitem__(Int.getInt(1)));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void __delitem__outOfBounds() {
        List l = new List();
        l.__delitem__(Int.getInt(0));
    }

    @Test
    public void __iter__() {
        List l = new List();
        org.python.Object iter = l.__iter__();
        assertTrue(iter instanceof List_Iterator);
    }

    @Test
    public void __reversed__() {
        List l = new List();
        org.python.Object reversedIter = l.__reversed__();
        assertTrue(reversedIter instanceof List_ReverseIterator);
    }

    @Test
    public void __contains__() {
        List l = createList(1, 2, 3, 4, 5);

        assertTrue(l.__contains__(Int.getInt(1)).toBoolean());
    }

    @Test
    public void __contains__emptyList() {
        List l = new List();
        assertFalse(l.__contains__(Int.getInt(1)).toBoolean());
    }

    @Test
    public void __contains__itemDoesntExist() {
        List l = createList(1, 2, 3, 4, 5);

        assertFalse(l.__contains__(Int.getInt(0)).toBoolean());
    }

    @Test
    public void __contains__bool() {
        List l = new List();
        l.append(Bool.getBool(true));
        l.append(Bool.getBool(false));

        // Since True=1 and False=0
        assertTrue(l.__contains__(Int.getInt(1)).toBoolean());
        assertTrue(l.__contains__(Int.getInt(0)).toBoolean());
        assertFalse(l.__contains__(Int.getInt(2)).toBoolean());
    }

    @Test
    public void __add__() {
        List l = new List();
        l.append(Int.getInt(1));
        List m = new List();
        m.append(Int.getInt(2));
        List n = (List) l.__add__(m);

        assertEquals(Int.getInt(1), n.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), n.__getitem__(Int.getInt(1)));
    }

    @Test
    public void __add__emptyList() {
        List l = new List();
        List m = new List();
        List n = (List) l.__add__(m);

        assertEquals(Int.getInt(0), l.__len__());
    }

    @Test
    public void __mul__integer() {
        List l = createList(1, 2, 3);

        List m = (List) l.__mul__(Int.getInt(2));

        assertEquals(m.__getitem__(Int.getInt(0)), m.__getitem__(Int.getInt(3)));
        assertEquals(m.__getitem__(Int.getInt(1)), m.__getitem__(Int.getInt(4)));
        assertEquals(m.__getitem__(Int.getInt(2)), m.__getitem__(Int.getInt(5)));
    }

    @Test
    public void __mul__emptyList() {
        List l = new List();

        List mul = (List) l.__mul__(Int.getInt(2));

        assertEquals(Int.getInt(0), mul.__len__());
    }

    @Test
    public void __mul__boolTrue() {
        List l = createList(1, 2);

        List mulTrue = (List) l.__mul__(Bool.getBool(true));

        assertEquals(Int.getInt(1), mulTrue.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), mulTrue.__getitem__(Int.getInt(1)));
    }

    @Test
    public void __mul__boolFalse() {
        List l = createList(1, 2);

        List mulFalse = (List) l.__mul__(Bool.getBool(false));

        assertEquals(Int.getInt(0), mulFalse.__len__());
    }

    @Test
    public void __imul__integer() {
        List l = createList(1, 2, 3);

        List m = (List) l.__imul__(Int.getInt(2));

        assertEquals(m.__getitem__(Int.getInt(0)), m.__getitem__(Int.getInt(3)));
        assertEquals(m.__getitem__(Int.getInt(1)), m.__getitem__(Int.getInt(4)));
        assertEquals(m.__getitem__(Int.getInt(2)), m.__getitem__(Int.getInt(5)));
    }

    @Test
    public void __imul__emptyList() {
        List l = new List();

        List mul = (List) l.__imul__(Int.getInt(2));

        assertEquals(Int.getInt(0), mul.__len__());
    }

    @Test
    public void __imul__boolTrue() {
        List l = createList(1, 2);

        List mulTrue = (List) l.__imul__(Bool.getBool(true));

        assertEquals(Int.getInt(1), mulTrue.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), mulTrue.__getitem__(Int.getInt(1)));
    }

    @Test
    public void __imul__boolFalse() {
        List l = createList(1, 2);

        List mulFalse = (List) l.__imul__(Bool.getBool(false));

        assertEquals(Int.getInt(0), mulFalse.__len__());
    }

    @Test
    public void append() {
        List l = new List();
        l.append(Int.getInt(1));
        l.append(new Str("a string"));
        l.append(Bool.getBool(true));

        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("a string"), l.__getitem__(Int.getInt(1)));
        assertEquals(Bool.getBool(true), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void clear() {
        List l = new List();
        l.append(Int.getInt(1));
        l.append(new Str("a string"));
        l.append(Bool.getBool(true));

        assertEquals(Int.getInt(3), l.__len__());
        l.clear();
        assertEquals(Int.getInt(0), l.__len__());
    }

    @Test
    public void clear_emptyList() {
        List l = new List();

        assertEquals(Int.getInt(0), l.__len__());
        l.clear();
        assertEquals(Int.getInt(0), l.__len__());
    }

    @Test
    public void copy() {
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
    public void copy_emptyList() {
        List l = new List();
        List lCopy = (List) l.copy();

        assertEquals(Int.getInt(0), l.__len__());
        assertEquals(Int.getInt(0), lCopy.__len__());
    }

    @Test
    public void count() {
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
    public void count_emptyList() {
        List l = new List();
        assertEquals(Int.getInt(0), l.count(new Str("a non-existing string")));
    }

    @Test
    public void extend_emptyList() {
        List l = new List();
        l.append(Int.getInt(1));

        List toExtend = new List();
        l.extend(toExtend);

        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
    }
    @Test
    public void extend_list() {
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
    public void extend_tuple() {
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
    public void extend_set() {
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
    public void extend_frozenSet() {
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
    public void extend_dict() {
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
    public void index() {
        List l = new List();
        l.append(new Str("a"));
        l.append(new Str("b"));
        l.append(new Str("c"));

        assertEquals(Int.getInt(0), l.index(new Str("a"), null, null));
    }

    @Test
    public void index_start() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(1), l.index(Int.getInt(2), Int.getInt(1), null));
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void index_startAfterValue() {
        List l = createList(1, 2, 3);

        l.index(Int.getInt(1), Int.getInt(1), null);
    }

    @Test
    public void index_startEnd() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(1), l.index(Int.getInt(2), Int.getInt(0), l.__len__()));
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void index_startEndBeforeValue() {
        List l = createList(1, 2, 3);

        l.index(Int.getInt(3), Int.getInt(0), Int.getInt(2));
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void index_notFound() {
        List l = createList(1, 2, 3);

        l.index(Int.getInt(5), null, null);
    }

    @Test
    public void insert_first() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(0), Int.getInt(4));
        assertEquals(Int.getInt(4), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void insert_second() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(1), Int.getInt(5));
        assertEquals(Int.getInt(5), l.__getitem__(Int.getInt(1)));
    }

    @Test
    public void insert_last() {
        List l = createList(1, 2, 3);

        l.insert(l.__len__(), Int.getInt(6));
        assertEquals(Int.getInt(6), l.__getitem__(Int.getInt(3)));
    }

    @Test
    public void insert_aboveLast() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(200), Int.getInt(7));
        assertEquals(Int.getInt(7), l.__getitem__(Int.getInt(3)));
    }

    @Test
    public void insert_negativeOne() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(-1), Int.getInt(8));
        assertEquals(Int.getInt(8), l.__getitem__(Int.getInt(2)));
    }

    @Test
    public void insert_negativeLength() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(-l.__len__().value), Int.getInt(9));
        assertEquals(Int.getInt(9), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void insert_negativeAbove() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(-200), Int.getInt(10));
        assertEquals(Int.getInt(10), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void insert_string() {
        List l = createList(1, 2, 3);

        l.insert(Int.getInt(0), new Str("hello"));
        assertEquals(new Str("hello"), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void insert_list() {
        List l = createList(1, 2, 3);

        List innerList = createList(1, 2);

        l.insert(Int.getInt(0), innerList);
        assertEquals(createList(1, 2), l.__getitem__(Int.getInt(0)));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void insert_charIndex() {
        List l = createList(1, 2, 3);

        l.insert(new Str('a'), Int.getInt(4));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void insert_listIndex() {
        List l = createList(1, 2, 3);

        l.insert(createList(1, 2, 3), Int.getInt(4));
    }

    @Test
    public void pop_noIndex() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(3), l.pop(null));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test
    public void pop_zeroIndex() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(1), l.pop(Int.getInt(0)));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test
    public void pop_negativeIndex() {
        List l = createList(1, 2, 3);

        assertEquals(Int.getInt(2), l.pop(Int.getInt(-2)));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void pop_emptyList() {
        List l = new List();

        l.pop(null);
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void pop_indexTooLarge() {
        List l = createList(1, 2, 3);

        l.pop(Int.getInt(3));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void pop_indexTooSmall() {
        List l = createList(1, 2, 3);

        l.pop(Int.getInt(-4));
    }

    @Test
    public void remove_integer() {
        List l = createList(1, 2, 3);
        l.remove(Int.getInt(1));

        assertEquals(createList(2, 3), l);
    }

    @Test
    public void remove_firstDuplicate() {
        List l = createList(1, 2, 2, 3, 2);
        l.remove(Int.getInt(2));

        assertEquals(createList(1, 2, 3, 2), l);
    }

    @Test
    public void remove_boolean() {
        List l = createList(Bool.TRUE, Bool.FALSE, Bool.TRUE, Bool.FALSE);
        l.remove(Int.getInt(1));

        assertEquals(createList(Bool.FALSE, Bool.TRUE, Bool.FALSE), l);
    }

    @Test(expected = org.python.exceptions.ValueError.class)
    public void remove_notInList() {
        List l = createList(1, 2);
        l.remove(Int.getInt(3));
    }

    @Test
    public void reverse_emptyList() {
        List l = new List();
        l.reverse();
        assertEquals(Int.getInt(0), l.__len__());
    }

    @Test
    public void reverse() {
        List l = createList(1, 2, 3, 4, 5);
        l.reverse();

        assertEquals(createList(5, 4, 3, 2, 1), l);
    }

    @Test
    public void sort_emptyList() {
        List l = new List();
        l.sort(null, null);
        assertEquals(Int.getInt(0), l.__len__());
    }

    @Test
    public void sort_inOrder() {
        List l1 = createList(9, 4, 7);
        List l2 = createList(new Str("beta"), new Str("theta"), new Str("alpha"));

        l1.sort(null, null);
        l2.sort(null, null);

        assertEquals(createList(4, 7, 9), l1);
        assertEquals(createList(new Str("alpha"), new Str("beta"), new Str("theta")), l2);
    }

    @Test
    public void sort_reverse() {
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
    public void sort_key() {
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
    public void sort_keyReversed() {
        List l = createList(new Str("abc"), new Str("bza"), new Str("cda"), new Str("daa"));

        try {
            final Function f = ListTest.getFunctionSecond();
            l.sort((org.python.Object) f, Bool.TRUE);

            assertEquals(createList(new Str("bza"), new Str("cda"), new Str("abc"), new Str("daa")), l);
        } catch (NoSuchMethodException e) {
            assertTrue(false);
        }
    }

    @Test
    public void isHashable_isFalse() {
        assertFalse(new List().isHashable());
    }

    @Test
    public void hashCode_notNull() {
        assertNotNull(new List().hashCode());
    }

    @Test(expected = org.python.exceptions.AttributeError.class)
    public void __hash___shouldThrowException() {
        new List().__hash__();
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnInt() {
        new List().__iadd__(Int.getInt(1));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnBool() {
        new List().__iadd__(Bool.getBool(true));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnEllipsis() {
        new List().__iadd__(Ellipsis.ELLIPSIS);
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnNone() {
        new List().__iadd__(NoneType.NONE);
    }

    @Test()
    public void __iadd___lists() {
        List l1 = new List();
        List l2 = new List();

        l1.append(Int.getInt(1));
        l2.append(Int.getInt(2));

        assertEquals(Int.getInt(1), l1.__len__());
        assertEquals(Int.getInt(1), l2.__len__());

        l1.__iadd__(l2);

        assertEquals(Int.getInt(1), l1.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), l1.__getitem__(Int.getInt(1)));
        assertEquals(Int.getInt(2), l1.__len__());
        assertEquals(Int.getInt(1), l2.__len__());
    }

    @Test()
    public void __iadd___listsReverse() {
        List l1 = new List();
        List l2 = new List();

        l1.append(Int.getInt(1));
        l2.append(Int.getInt(2));

        assertEquals(Int.getInt(1), l1.__len__());
        assertEquals(Int.getInt(1), l2.__len__());

        l2.__iadd__(l1);

        assertEquals(Int.getInt(2), l2.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(1), l2.__getitem__(Int.getInt(1)));
        assertEquals(Int.getInt(2), l2.__len__());
        assertEquals(Int.getInt(1), l1.__len__());
    }

    @Test()
    public void List_empty() {
        assertNotNull(new List());
        assertEquals(Int.getInt(0), new List().__len__());
    }

    @Test()
    public void List_withList() {
        List l1 = new List();
        l1.append(Int.getInt(1));
        l1.append(Int.getInt(2));
        l1.append(Int.getInt(3));

        java.util.Map<java.lang.String, org.python.Object> kwargs = new java.util.HashMap<java.lang.String, org.python.Object>();
        org.python.Object[] args = {l1};

        List l2 = new List(args, kwargs);

        assertEquals(Int.getInt(1), l2.value.get(0));
        assertEquals(Int.getInt(2), l2.value.get(1));
        assertEquals(Int.getInt(3), l2.value.get(2));
    }

    @Test()
    public void List_withSet() {
        java.util.Set<org.python.Object> s = new java.util.HashSet<org.python.Object>();
        Int i1 = Int.getInt(1);
        Int i2 = Int.getInt(2);

        s.add(i1);
        s.add(i2);

        org.python.Object[] args = {new org.python.types.Set(s)};
        java.util.Map<java.lang.String, org.python.Object> kwargs = new java.util.HashMap<java.lang.String, org.python.Object>();

        List l1 = new List(args, kwargs); // kwargs doesn't seem to be used by List but let's pass them anyway

        assertEquals(Int.getInt(2), l1.__len__());
        assertEquals(i1, l1.__contains__(i1));
        assertEquals(i1, l1.__contains__(i2));
    }

    @Test()
    public void List_withEmptySet() {
        org.python.Object[] args = {new org.python.types.Set()};
        java.util.Map<java.lang.String, org.python.Object> kwargs = new java.util.HashMap<java.lang.String, org.python.Object>();

        List l1 = new List(args, kwargs); // kwargs doesn't seem to be used by List but let's pass them anyway

        assertEquals(Int.getInt(0), l1.__len__());
    }

    /*
     * __bool__()
     */

    @Test()
    public void __bool__empty() {
        List l1 = new List();

        assertEquals(org.python.types.Bool.FALSE, l1.__bool__());
    }

    @Test()
    public void __bool__str() {
        List l1 = new List();

        l1.append(new Str("foo"));
        assertEquals(org.python.types.Bool.TRUE, l1.__bool__());
    }

    @Test()
    public void __bool__multipleTypes() {
        List l1 = new List();

        l1.append(new Str("foo"));
        l1.append(Int.getInt(0));
        l1.append(new List());
        assertEquals(Bool.TRUE, l1.__bool__());
    }

    @Test()
    public void __bool__None() {
        List l1 = new List();

        l1.append(NoneType.NONE);
        assertEquals(Bool.TRUE, l1.__bool__());
    }

    @Test()
    public void __bool__EmptyList() {
        List l1 = new List();

        l1.append(new List());
        assertEquals(Bool.TRUE, l1.__bool__());
    }
}
