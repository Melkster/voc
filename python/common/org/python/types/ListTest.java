package org.python.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ListTest {
    @Test
    public void toJava_notNull() {
        assertNotNull(new List().toJava());
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
}

