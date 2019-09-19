package org.python.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ListTest {
    @Test
    public void toJava() {
        List l = new List();
        assertNotNull(l.toJava());
    }

    @Test
    public void isHashable() {
        List l = new List();
        assertFalse(l.isHashable());
    }

    @Test(expected = org.python.exceptions.AttributeError.class)
    public void __hash__() {
        new List().__hash__();
    }
}

